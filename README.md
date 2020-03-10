# KBS
Programming stimulator. KeyBoardSound ! :)

# Configuration `config.json`
```
{
  "clickSound": "click1.wav", // Name of typing sound 
  "framePos": 800, // Position in frames (nanoseconds) to start from 
  "clickVolume": 1, // 0..1 Keyboard typing (Clicking) sound volume
  "tapsPerTick": 1, // How much will be Tap sound repeated per 4 Seconds (Tick). [ 0 - to disable ]
  "tapSound": "tap1.wav", // Tapping sound name
  "tapVolume": 0.05 // 0..1 Tapping sound volume 
}
```

# Sounds
Sounds must be in `.wav` format.
They are located inside `sounds` folder. So you can manage and put your own sounds to it.