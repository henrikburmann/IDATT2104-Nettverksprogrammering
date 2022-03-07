FROM gcc:latest

RUN mkdir /cpp
COPY temp/code.cpp /cpp/code.cpp
WORKDIR /cpp
RUN g++ -o code /cpp/code.cpp

CMD [ "./code" ]