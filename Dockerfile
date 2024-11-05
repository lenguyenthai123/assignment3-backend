FROM ubuntu:latest
LABEL authors="ThinkBook"

ENTRYPOINT ["top", "-b"]