# Basketball NBA Assignment - Lesson 16

## Author: Devyn Benson

This assignment implements a Basketball-themed object-oriented programming solution that meets all the requirements for Lesson 16.

## Assignment Requirements Met

**Custom Class**: `Basketball` class represents a real-world NBA basketball  
**5+ Member Variables** of **3+ Different Types**:
- `String brand` (String type)
- `double circumference` (double type)  
- `int gamesPlayed` (int type)
- `boolean isOfficialSize` (boolean type)
- `List<String> playersUsed` (Collection type - ArrayList)
- `Position primaryPosition` (Enum type)

**Enum Type**: `Position` enum with NBA player positions  
**Constructor**: Basketball constructor with validation  
**3+ Member Functions**:
- `getPerformanceRating()` - Uses conditional expression
- `addPlayerUsage()` - Uses collection member variable  
- `getPlayersReport()` - Uses a loop

**Custom Exception**: `InvalidStatException` for validation errors  
**5+ Test Methods**: `BasketballTest` class with comprehensive tests

## Classes Overview

### Basketball Class
Represents an NBA basketball with player statistics and game tracking capabilities.

**Key Features:**
- Tracks brand, circumference, games played, and player usage
- Validates basketball specifications (official size detection)
- Maintains list of players who have used the basketball
- Performance rating based on game usage with conditional expressions
- Loop-based player reporting functionality

### Position Enum
Defines NBA player positions:
- POINT_GUARD ("PG", "Point Guard")
- SHOOTING_GUARD ("SG", "Shooting Guard")  
- SMALL_FORWARD ("SF", "Small Forward")
- POWER_FORWARD ("PF", "Power Forward")
- CENTER ("C", "Center")

### InvalidStatException
Custom exception thrown for invalid basketball statistics:
- Negative or zero circumference
- Invalid player names (null/empty)
- Game limit exceeded (1000+ games)

## Code Examples

### Creating a Basketball
```java
Basketball spalding = new Basketball("Spalding Official NBA", 29.7, Position.CENTER);
```

### Adding Players and Tracking Usage
```java
spalding.addPlayerUsage("Shaquille O'Neal");
spalding.addPlayerUsage("Joel Embiid");
spalding.incrementGamesPlayed();
```

### Performance Rating (Conditional Expression)
```java
String rating = basketball.getPerformanceRating();
// Returns: "Low Usage", "Medium Usage", or "High Usage"
```

### Player Report (Uses Loop)
```java
String report = basketball.getPlayersReport();
// Generates numbered list of all players
```

## Testing
The `BasketballTest` class includes 10 comprehensive test methods covering:
- Constructor validation
- Performance rating calculations
- Player usage management
- Exception handling
- Official size detection
- Game tracking limits

## Running the Demo
Run `BasketballDemo.main()` to see the Basketball class in action with NBA stars and real-world scenarios.

## Files Created
- `Basketball.java` - Main basketball class
- `Position.java` - NBA position enum
- `InvalidStatException.java` - Custom exception
- `BasketballTest.java` - Comprehensive test suite  
- `BasketballDemo.java` - Interactive demonstration
- `README.md` - This documentation

All tests pass successfully!
