# AquaCoreBreak (acb)

AquaCoreBreak is a Minecraft 1.21 Paper plugin that modifies block drops when they are broken without tools.

## Features

- **Per-Block Tool Overrides**: Specify allowed tools for each block in the whitelist.
- **Tool Bypass**: Define global tools that bypass the plugin logic (vanilla behavior) if no specific tool is defined for a block.
- **World Support**: Enable the plugin only in specific worlds.
- **Drop Modes**:
    - `DEFAULT`: Vanilla drops.
    - `NONE`: No drops.
    - `COMMAND`: Execute a command.
    - `CUSTOM`: Configurable drops with chances.
- **Creative Mode Support**: Plugin is disabled in Creative mode.
- **Config Versioning**: Automatically updates configuration files while preserving settings.

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

# Global tools that bypass plugin logic if no specific tool is defined for a block
tool-items:
  - DIAMOND_PICKAXE

whitelist:
  STONE:
    mode: NONE # No drops when broken by hand (default behavior if no tool specified)
  DIRT:
    mode: DEFAULT
    tool: STONE_SHOVEL # Only drops if broken with Stone Shovel
  GRAVEL:
    mode: DEFAULT
    tool: GOLDEN_PICKAXE, NONE # Drops if broken with Golden Pickaxe OR Hand
  GRASS_BLOCK:
    mode: CUSTOM
    drops:
      rare_diamond:
        item: DIAMOND
        amount: 1
        chance: 0.05 # 5% chance
```
