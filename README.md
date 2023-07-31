# Gaia Dimension
[![Discord](https://img.shields.io/discord/404597598633328643.svg)](https://discord.gg/g7BBHB6)

Basked under an eternal sun, a world preserved in time, a land sprouting with crystals and minerals, the ground seeping a mysterious energy. Welcome to Gaia.

This is the official repository of the Gaia Dimension mod. Report bugs, create pull requests, or just browse the code at your own pace.

## Contributing
Before starting any contribution, make sure to read up on the Contributing Guidelines.

Setting up the project is as simple as opening build.gradle as a new project.

## Maven
Gaia Dimension is hosted on a maven for use in downloading for projects.

To set it up, add this to your build.gradle:
```
repositories {
    maven {
        url 'https://maven.tamaized.com/releases/'
    }
}

dependencies {
    implementation fg.deobf("androsa:gaiadimension:${version}"
}
```

`${version}` may be swapped out for any valid configuration of the mod's version, that is (minecraft version)-(mod version).
