grammar ProtoGen;

@header {
    package com.kdsc.protogen.antlr;
}

file:
    (
        protogen_type |
        protogen_key |
        protogen_enum |
        protogen_one_of
    )*
    EOF;

protogen_type:
    'type' 'interface'? namespace_name_generic_parameters_with_bounds implements_list? ( '{' (versions | fields)? '}' )?;

protogen_key:
    'key' 'interface'? namespace_name_generic_parameters_with_bounds implements_list? ( '{' (versions | fields)? '}' )?;

protogen_enum:
    'enum' namespace_name ( '{' (enum_versions | enum_cases)? '}' )?;

protogen_one_of:
    'oneof' namespace_name ( '{' (enum_versions | enum_cases)? '}' )?;

enum_versions:
    enum_version+;

enum_version:
    'version' version_number ( '{' enum_cases? '}' )?;

enum_cases:
    enum_name+ (enum_name)*;

implements_list:
    ':' namespace_name_generic_parameters_without_bounds (',' namespace_name_generic_parameters_without_bounds)*;

versions:
    version+;

version:
    'version' version_number generic_parameters_with_bounds? implements_list? ( '{' fields? '}' )?;

fields:
    field+;

field:
    field_name ':' field_type;

field_type:
    'optional'?
    (
        array |
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
        'decimal' |
        'date' |
        'datetime' |
        'localdatetime' |
        map |
        set |
        value_or_error |
        namespace_name_generic_parameters_without_bounds |
        generic_parameter_without_bounds
    );

map:
    'map' '<' field_type ',' field_type '>';

set:
    'set' '<' field_type '>';

value_or_error:
    'valueorerror' '<' field_type '>';

array:
    non_array_field_type ('[' ']')+;

namespace_name_generic_parameters_with_bounds:
    namespace_name generic_parameters_with_bounds?;

namespace_name_generic_parameters_without_bounds:
    namespace_name generic_parameters_without_bounds?;

namespace_name:
    (namespace '.')+ name;

generic_parameters_with_bounds:
    '<' generic_parameter_with_bounds (',' generic_parameter_with_bounds)* '>';

generic_parameters_without_bounds:
    '<' generic_parameter_without_bounds (',' generic_parameter_without_bounds)* '>';

generic_parameter_with_bounds:
    IDENTIFIER (':' namespace_name_generic_parameters_without_bounds)? ('&' namespace_name_generic_parameters_without_bounds)*;

generic_parameter_without_bounds:
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
    [1-9][0-9]*;

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