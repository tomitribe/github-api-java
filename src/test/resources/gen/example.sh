#!/bin/bash

P="${1?Specify a API path}"
{
echo -n "\"$P\": "
cat api.github.com.json| jq ".components.examples.\"$P\""
} | pbcopy && pbpaste
