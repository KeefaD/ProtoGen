grammar ProtoGen;

@header {
    package com.kdsc.protogen.antlr;
}

file:
    (
        protogen_type |
        protogen_enum
    )*
    EOF;

protogen_type:
    'type' namespace_name_generic_parameters implements_list? ( '{' (type_versions | type_fields)? '}' )?;

protogen_enum:
    'enum' namespace_name ( '{' (enum_versions | enum_cases)? '}' )?;

enum_versions:
    enum_version+;

enum_version:
    'version' version_number ( '{' enum_cases? '}' )?;

enum_cases:
    enum_name+ (enum_name)*;

implements_list:
    ':' namespace_name_generic_parameters (',' namespace_name_generic_parameters)*;

type_versions:
    type_version+;

type_version:
    'version' version_number generic_parameters? implements_list? ( '{' type_fields? '}' )?;

type_fields:
    type_field+;

type_field:
    field_name ':' field_type;

field_type:
    (
        array_field_type |
        non_array_field_type
    );

non_array_field_type:
    (
        'double' |
        'float' |
        'int32' |
        'int64' |
        'bool' |
        'string' |
        'bytes' |
        map_field_type |
        set_field_type |
        namespace_name_generic_parameters |
        generic_parameter
    );

map_field_type:
    'map' '<' field_type ',' field_type '>';

set_field_type:
    'set' '<' field_type '>';

array_field_type:
    non_array_field_type ('[' ']')+;

namespace_name_generic_parameters:
    namespace_name generic_parameters?;

namespace_name:
    (namespace '.')+ name;

generic_parameters:
    '<' generic_parameter (',' generic_parameter)* '>';

generic_parameter:
    IDENTIFIER;

namespace:
    IDENTIFIER;

name:
    IDENTIFIER;

field_name:
    IDENTIFIER;

enum_name:
    IDENTIFIER;

version_number:
    VERSION_NUMBER;

VERSION_NUMBER:
    [0-9]+;

IDENTIFIER:
    [a-zA-Z_]+[0-9a-zA-Z_]*;

WHITESPACE:
    [ \t\r\n]+ -> skip;

SINGLE_LINE_COMMENT:
    '//' ~[\n\r]* -> skip;

MULTI_LINE_COMMENT:
    '/*'.*?'*/' -> skip;

UNKNOWN_CHAR:
    .;