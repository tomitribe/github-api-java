#!/bin/bash

COMPONENT="${1?Specify a component reference in the form of \"#/components/parameters/per_page\"}"

D="$(dirname $0)"
cd "$D"


  if [[ "$COMPONENT" == *"/parameters/"* ]]; then ./parameter.sh "${COMPONENT##*/}"
elif [[ "$COMPONENT" == *"/examples/"* ]]; then ./example.sh "${COMPONENT##*/}"
elif [[ "$COMPONENT" == *"/headers/"* ]]; then ./header.sh "${COMPONENT##*/}"
elif [[ "$COMPONENT" == *"/parameters/"* ]]; then ./parameter.sh "${COMPONENT##*/}"
elif [[ "$COMPONENT" == *"/responses/"* ]]; then ./response.sh "${COMPONENT##*/}"
elif [[ "$COMPONENT" == *"/schemas/"* ]]; then ./schema.sh "${COMPONENT##*/}"
else ./path.sh "$COMPONENT"
fi
