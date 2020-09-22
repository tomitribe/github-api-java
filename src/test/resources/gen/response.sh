#!/bin/bash

P="${1?Specify a API path}"
cat api.github.com.json| jq ".components.responses.\"$P\""
