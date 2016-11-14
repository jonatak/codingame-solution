import           System.IO

main :: IO ()
main = do
    hSetBuffering stdout NoBuffering -- DO NOT REMOVE

    road <- fmap read getLine -- the length of the road before the gap.
    gap <- fmap read getLine
    platform <- fmap read getLine

    loop road gap platform

loop :: Int -> Int -> Int -> IO ()
loop road gap platform = do
    speed <- fmap read getLine
    coordx <- fmap read getLine

    let action
          | coordx >= (road + gap) = "SLOW"
          | (coordx + speed) >= (road + gap) = "JUMP"
          | speed > (gap + 1) = "SLOW"
          | speed < (gap + 1) = "SPEED"
          | otherwise = "WAIT"

    putStrLn action

    loop road gap platform
