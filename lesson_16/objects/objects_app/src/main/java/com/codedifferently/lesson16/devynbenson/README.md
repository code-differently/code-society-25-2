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
**Test Coverage**: Comprehensive test suite with 32 tests across 4 test classes:
- `BasketballTest` - 16 unit tests covering all Basketball functionality
- `PositionTest` - 7 tests covering all Position enum values and methods
- `InvalidStatExceptionTest` - 3 tests covering custom exception functionality  
- `BasketballIntegrationTest` - 5 integration tests covering real-world scenarios

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
The test suite includes 32 comprehensive test methods across 4 test classes:

### Unit Tests
- **BasketballTest** (16 tests): Complete coverage of Basketball class functionality
- **PositionTest** (7 tests): Full coverage of Position enum values and methods  
- **InvalidStatExceptionTest** (3 tests): Exception constructor and inheritance testing

### Integration Tests  
- **BasketballIntegrationTest** (5 tests): Real-world NBA scenarios including:
  - Complete game simulation with multiple players and positions
  - Season-long basketball usage tracking
  - Complex exception handling scenarios
  - All position and size variation testing
  - Basketball demo scenario replication

### Test Coverage Areas
- Constructor validation and edge cases
- Performance rating calculations across all usage levels
- Player usage management and collection operations
- Exception handling for all error conditions
- Official size detection for various circumferences
- Game tracking and limits (up to 1000 games)
- String representation and data encapsulation
- Enum functionality and value verification
- Integration between Basketball, Position, and InvalidStatException classes

## Running the Demo
Run `BasketballDemo.main()` to see the Basketball class in action with NBA stars and real-world scenarios.

## Files Created
- `Basketball.java` - Main basketball class
- `Position.java` - NBA position enum
- `InvalidStatException.java` - Custom exception
- `BasketballTest.java` - Unit tests for Basketball class (16 tests)
- `PositionTest.java` - Unit tests for Position enum (7 tests)
- `InvalidStatExceptionTest.java` - Unit tests for custom exception (3 tests)
- `BasketballIntegrationTest.java` - Integration tests (5 tests)
- `BasketballDemo.java` - Interactive demonstration
- `README.md` - This documentation

All 32 tests pass successfully! Code coverage improved from 50% to 60%.
