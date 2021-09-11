grammar ProtoGen;

@header {
    package com.kdsc.protogen.antlr;
}

file:
    (
        type
    )*
    EOF;

type:
    'type' namespace_name_generic_parameters implements_list? ( '{' (type_versions | type_fields)? '}' )?;

implements_list:
    ':' namespace_name_generic_parameters (',' namespace_name_generic_parameters)*;

type_versions:
    type_version+;

type_version:
    'version' VN generic_parameters? implements_list? ( '{' type_fields '}' )?;

type_fields:
    type_field+;

type_field:
    ID ':' type_field_type;

type_field_type:
    (
        'int32' |
        namespace_name_generic_parameters |
        generic_parameter
    );

namespace_name_generic_parameters:
    namespace_name generic_parameters?;

namespace_name:
    (namespace '.')+ name;

generic_parameters:
    '<' generic_parameter (',' generic_parameter)* '>';

generic_parameter:
    ID;

namespace:
    ID;

name:
    ID;

field:
    ID;

VN:
    [0-9]+;

ID:
    [a-zA-Z_]+[0-9a-zA-Z_]*;

WS:
    [ \t\r\n]+ -> skip;

SLC:
    '//' .*? ('\n'|'\r')* -> skip;

MLC:
    '/*'.*?'*/' -> skip;