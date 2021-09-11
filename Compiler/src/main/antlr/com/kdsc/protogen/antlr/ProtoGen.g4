grammar ProtoGen;

@header {
    package com.kdsc.protogen.antlr;
}

file:
    value;

value:
    'value' name;

name:
    ID;

ID:
    [a-z]+;

WS:
    [ \t\r\n]+ -> skip;