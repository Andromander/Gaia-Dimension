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

### Datagen all new assets
Adding a new feature is one thing, but it's useless if the required assets are not created. These must always be done through data generation. Some exclusions to this clause:
- Retextures. Although unlikely to be accepted, remakes of existing textures aren't required for datagen
  - However, if the new assets require new models, these new models must be accommodated via datagen.
- Custom models. Datagen covers the basic models, however if the new model is completely custom right down to building new voxels, this may be omitted. These models *must* work, ie. no errors in log.
- Non-datagen assets. If the new asset does not have an applicable data generator, continue as usual.

### Regarding Textures, make sure they are 16*16 pixels
This might be a mod, but it needs to stay true to Minecraft's art style. Do not exceed 16 x 16 unless specified by the author. Some textures - especially scaled mobs, may have a seemingly incorrect ratio of pixel to voxel. Do not make any attempt to fix the discrepency as often this results in uncanny or just ugly appearances. Let the low resolution do the interpretation.
Not unless you also volunteer to remodel the entity at the same time.

### Regarding Translations, make sure that it is sound quality
I cannot understand languages outside of English, so if you are ready to have your lang file pulled, please ping the author that you are ready to do so. Lang files should also be formatted as `xx_xx`, as it is the current format. Any reports of low quality or troll translations will be immediately reverted or deleted, as well as you, the contributor, risk being banned from future contributions.

### It needs to look good
The requisite for this will vary. Some textures may be slated for retextures but lack time to produce, but if you are able to snuff these out and provide new and better textures, feel free to make an attempt.
Do note that, while some textures may not appear of sound quality, may be designed in that way intentionally or is very unlikely to change for identity reasons.
Some conditions for passing include:
- Colour perception. If it appears identical to another texture, make sure the colour values are different enough on a monochrome level.
- Colour values. Hue shifting for shadows is a common practice, however becomes meaningless when the highlights, midtones, and shadows are too similar.
- Unique and detailed. If the new texture can communicate something new while still maintaining their identifying traits, it may likely be accepted.
- Personal opinion. If the response is "don't like", either follow feedback or discard and restart. The latter is not likely to happen, but if ever uttered, it should be taken note of.

## Testing
Testing the mod is essential to making sure that the mod is functional outside of the test environment. There aren't as many major guidelines to follow, but if you come across a bug, try to use the Issue template provided. Suggestions are best directed to Gaia Dimension's Discord.
