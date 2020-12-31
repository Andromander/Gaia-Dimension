# How To Contribute
There are various ways to contribute to the development of the Gaia Dimension mod. Some common types of contributions can include donating code, making textures, translating, and testing. Please note that any Pull Requests (PRs) made can and will be rejected if they do not comply with the following guidelines.

## Code
Though it is not easy, donating code is the best way to contribute. However, there are a few things to note when submitting a PR with code.

### Make sure braces are on *the same line*
As much as some people are inclined to, and for consistency's sake, make sure your code is formatted to use same-line braces. This is to reduce on file size and better vertical readability.

Example:
```java
public void() {
}
```
### Spacing must use four spaces, not tabs
Spacing must be 4 spaces: It seems trivial to bring this up, but please stick to 4 spaces per indentation as this is the project default. It helps when reviewing changes made to the source, as changes to indentations makes it appear like the entire file has been replaced.

### It needs to work
You can submit a PR that's got a solution to a problem or a new feature, but it needs to build. This can be checked by submitting the PR's and looking at the mergability. This repo uses [Travis-CI](https://travis-ci.org/), so builds are often checked.
That being said, it also needs to be functional in-game. You could submit a PR based on the grounds that it works, but thorough testing is required to make sure that it doesn't break any other feature.

## Textures and Models
Though they are fundamental, contributing to these isn't as desired by developers due to how easy it is to contribute this way. Just like coding, however, there are a few guidelines that need to be followed.

### Please try to submit the JSON for the texture you wish to add
If something is implemented but untextured, you may wish to add a texture to those. However, it is most desirable if a JSON is submitted this way too. Unless specified, a PR is unlikely to be pulled if a texture is added but no JSON is attached.

# Use Data Generators for Blockstates and Models where possible
To save on time and energy, most blocks and items have generated files where they are output to src/generated. Unless the block or item has a unique model that would take more time to write a generator for, please use the Data Generators provided in src/main/java/androsa/gaiadimension/data/.

### Only make 16x16 pixel textures
This might be a mod, but it needs to stay true to Minecraft's art style. Do not exceed 16x16 unless specified by the author.


### It needs to look good
This is a no-brainer. To filter out random "contributors" from the mod, the texture needs at least *some* effort put into it. Try not to use third-party editors, such as [Novaskin](https://novaskin.me/), as they are limited in tools. Recommended tools are [Paint.NET](https://www.getpaint.net/), [GIMP](https://www.gimp.org/) and [Photoshop](https://photoshop.com/).

## Localization and Translating

Most contributors cannot understand languages outside of English, so if you are ready to have your `.lang` file pulled, please notify the author that you are ready to do so.

### Naming conventions
`.json` language files should be formatted as `xx_xx.json`, as it is the current and recommended format. (Example: `en_US.json`)

## Other Assets Not Yet Covered By This Document

### It needs to work
You can submit a PR that's got a solution to a problem or a new feature, but it needs to build. This can be checked by submitting the PR's and looking at the mergability. This repo uses [Travis-CI](https://travis-ci.org/), so builds are often checked.
That being said, it also needs to be functional in-game. You could submit a PR based on the grounds that it works, but thorough testing is required to make sure that it doesn't break any other feature.

# It Needs to Fit With the Mod's Theme

It has to fit with the scope of the mod and its theme.

# Testing
Testing the mod is essential to making sure that the mod is functional outside of the test environment. There aren't as many major guidelines to follow, but if you come across a bug, try to use the issue templates provided. Suggestions are best directed to [Gaia Dimension's Discord](https://discord.gg/g7BBHB6).
