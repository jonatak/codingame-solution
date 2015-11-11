import           Control.Monad
import           System.IO

closer :: String -> Int -> String -> Int -> String
closer x1 y1 x2 y2 = if y1 < y2 then x1 else x2

main :: IO ()
main = do
    hSetBuffering stdout NoBuffering
    loop

loop :: IO ()
loop = do
    enemy1  <- getLine -- name of enemy 1
    dist1   <- fmap read getLine
    enemy2  <- getLine
    dist2   <- fmap read getLine

    putStrLn $ closer enemy1 dist1 enemy2 dist2

    loop
