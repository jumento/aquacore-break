# AquaCoreBreak (acb)

AquaCoreBreak is a Minecraft 1.21 Paper plugin that modifies block drops when they are broken without tools.

## Features

- **Hand-Breakable Control**: Define which blocks are affected when broken by hand.
- **Tool Bypass**: Define tools that bypass the plugin logic (vanilla behavior).
- **World Support**: Enable the plugin only in specific worlds.
- **Drop Modes**:
    - `DEFAULT`: Vanilla drops.
    - `NONE`: No drops.
    - `COMMAND`: Execute a command.
    - `CUSTOM`: Configurable drops with chances.
- **Creative Mode Support**: Plugin is disabled in Creative mode.

## Installation

1. Build the plugin using `./gradlew build`.
2. Place the generated JAR file (in `build/libs`) into your server's `plugins` folder.
3. Restart the server.

## Commands

- `/acb reload`: Reloads the configuration file.
    - Alias: `/aquacorebreak reload`
    - Permission: `aquacorebreak.admin`

## Configuration

The `config.yml` file allows you to customize the plugin behavior.

### Example Configuration

```yaml
enabled-worlds:
  - world

tool-items:
  - DIAMOND_PICKAXE

hand-breakable-blocks:
  - STONE
  - GRASS_BLOCK

whitelist:
  STONE:
    mode: NONE # No drops when broken by hand
  GRASS_BLOCK:
    mode: CUSTOM
    drops:
      rare_diamond:
        item: DIAMOND
        amount: 1
        chance: 0.05 # 5% chance
```
