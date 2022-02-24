# PlayerActivity

Displays the list of recently active players with `/players` command.

# Requirements

- Bukkit **1.15.2-R0.1-SNAPSHOT** or newer (works with Spigot too).

# Commands

| Command        | Description                              | Example  |
| -------------- | ---------------------------------------- | -------- |
| ```/players``` | Displays list of recently active players | /players |

# Permissions

| Permission             | Description                                 |
| ---------------------- | ------------------------------------------- |
| playeractivity.\*      | Gives access to all PlayerActivity commands |
| playeractivity.players | Gives players access to /players command    |

# Configuration

```yaml
# Player list settings
list:
  # Display also online players when executing /players command (by default, true -> shows both online and offline players)
  displayOnlinePlayers: true
  # If true, shows only amount of players specified in maxPlayers variable bellow
  limitListLength: true
  # Maximum number of players to display on list (if limitListLength = true)
  maxPlayers: 9

# MySQL database settings
mysql:
  # MySQL server address and port example "mysql.server.com:3306"
  serverAddress: address:port
  # Database name (on the server)
  databaseName: dbName
  # Server username
  username: username
  # Server password
  password: pwd
```


