# ProtoGen

##Features

1. Generics for protobuf
2. Versioning strategies
3. Immutable
4. Deep equals
5. Perhaps covariant and ccntravarient collections
6. Type libraries, version catalogues, guids

##Notes
1. Do it all in one version like midi
2. Ridiculous amount of tests, but clever ones
3. Ridiculous amount of documentation, with real world examples
4. Extension points and example of using them
5. Wide open, jump in at any point like Spring
6. Amazing errors with the compiler
7. As simple as possible
8. Antrl tests


##Outstanding tasks
1. Semantic analyser
2. Transformer
    -> Bucket for imports, but make the imports protogen imports (maybe)
3. Code generator
4. Create generated code test
5. Normal generated code test
6. Generated docs
7. What about lists
8. Name escaping


# ProtoBoxes

##Features
1. Configurable boxes to put data in for big data
2. Only value is proto type
3. All other fields are readable without type library
4. Valid time, transaction time?

##Default compartments
1. Primary Key -> Dictionary but convertable to type
2. Secondary Indexes -> Dictionary
3. Context? -> Dictionary
4. Metadata -> Dictionary
5. Value -> Not dictionary
6. Type library version to read and object version

Generic box to put protogen items into, configurable
