## Using Generated Files
Files in this directory are generated from the data package and, therefore, should _not_ be modified by hand.
If a file in this directory needs to be modified, please use the respective generators in the data package.
If a file cannot be modified via a generator, remove it from the generator and place them in the main's resource directory. Generated is specifically for automated files.

### Generating Biomes
As there is a bug regarding ConfiguredFeatures not appearing to be registered when generated, any generated biomes here should instead be used as a template and the complete file should be moved to the main's resource directory.
Because of this, generating biomes will be handled as such:
1. The biome is to be generated with the parameters it normally should.
2. Once generated, delete any generated biomes that are not modified. Take the modified biome files and replace the direct feature implementations with ConfiguredFeature json.
3. If the ConfiguredFeature does not have a json file with it, create it in the respective directory in main.
4. Once modifications are complete, move to main's resource directory.

*Failure to comply with these guidelines may result in a rejected or closed PR.*