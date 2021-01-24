# How To Contribute
Contributing to Gaia Dimension has various ways to contribute to the development. From a standpoint, contributions can include Coding, Assets and Testing. Please note that any PRs made can and will be rejected if they do not comply with the following guidelines. PRs will be closed if:

* Does not comply with the listed guielines
* No action is done to resolve conflicts or build failures within 2 weeks

## Coding
Though it is not easy from a surface level, it's worth more to contribute via coding than any other place. However, there are a few things to note when submitting a PR for coding.

### Make sure braces are on *the same line*
As much as some people are inclined to, and for consistency's sake, make sure your code is formatted to use same-line braces. This is to reduce on file size and better vertical readability.

Example:
```
public void() {
}
```
### It needs to work
You can submit a PR that's got a solution to a problem or a new feature, but it needs to build. This can be checked by submitting the PR's and looking at the mergability. This repo uses Travis-CI, so builds are often checked.
That being said, it also needs to be functional in-game. You could submit a PR based on the grounds that it works, but thorough testing is required to make sure that it doesn't break any other feature.

## Assets
Though they are fundamental, contributing to these isn't as desired due to how easy it is to contribute this way. Just like Coding, there are a few guidelines that need to be followed.

### Please try to submit the JSON for the texture you wish to add
If something is implemented but untextured, you may wish to add a texture to those. However, it is most desirable if a JSON is submitted this way too. Unless specified, a PR is unlikely to be pulled if a texture is added but no JSON is attached.

### Regarding JSONS, use Forge Blockstates
If you are submitting a PR for a block alongside a JSON, please be sure to use the Forge Blockstate. These reduce on file count and is the desired format. Unless the model does not exist for the block, try to use the existing models from Minecraft.

### Regarding Textures, make sure they are 16*16 pixels
This might be a mod, but it needs to stay true to Minecraft's art style. Do not exceed 16*16 unless specified by the author.

### Regarding Translations, make sure that it is sound quality
The developers cannot understand languages outside of English, so if you are ready to have your lang file pulled, please ping the author that you are ready to do so. Lang files should also be formatted as `xx_xx`, as it is the current format.

### It needs to look good
This is a no-brainer. To filter out random "contributors" from the mod, the texture needs at least *some* effort put into it. Try not to use third-party editors like Novaskin, as they are limited in tools. Recommended tools are Paint.NET, GIMP and Photoshop.

## Testing
Testing the mod is essential to making sure that the mod is functional outside of the test environment. There aren't as many major guidelines to follow, but if you come across a bug, try to use the Issue template provided. Suggestions are best directed to Gaia Dimension's Discord
