#!/bin/bash
PROJECT_ROOT=$(dirname "$(realpath "$0")")

ENV_FILE="${PROJECT_ROOT}/.env"
TEMPLATE_FILE="${PROJECT_ROOT}/src/main/resources/META-INF/persistence.xml.template"
OUTPUT_FILE="${PROJECT_ROOT}/src/main/resources/META-INF/persistence.xml"
echo $TEMPLATE_FILE

# Check if the .env file exists
if [ ! -f "$ENV_FILE" ]; then
    echo ".env file not found!"
    exit 1
fi

# Check if template file exists
if [ ! -f "$TEMPLATE_FILE" ]; then
    echo "Template file not found!"
    exit 1
fi

# Recreate output file
rm -f "$OUTPUT_FILE" && cp "$TEMPLATE_FILE" "$OUTPUT_FILE"

# Load environment variables from the .env file
export $(grep -v '^#' "$ENV_FILE" | xargs)

# Replace placeholders with environment variables
for var in $(grep -o '\${\([^}]*\)}' "$TEMPLATE_FILE" | sed 's/[${}]//g' | sort -u); do
    value="${!var}"
    if [ -z "$value" ]; then
        echo "Warning: Environment variable $var is not set."
    fi
    # Replace placeholder with the actual value
    awk -v var="$value" '{gsub(/\${'$var'}/, var)}1' "$OUTPUT_FILE" > tmpfile && mv tmpfile "$OUTPUT_FILE"
done

echo "persistence.xml has been generated successfully. Building..."

"${PROJECT_ROOT}/gradlew" clean build
cp -f "${PROJECT_ROOT}/build/libs/db-provider-plain.jar" "${PROJECT_ROOT}/.providers"

echo "Build done. Cleaning up..."
rm -f "$OUTPUT_FILE"