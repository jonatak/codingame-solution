import           Control.Monad
import           System.IO

nexty :: Int -> String
nexty 0    = ""
nexty 1    = "S"
nexty (-1) = "N"

nextx :: Int -> String
nextx 0    = ""
nextx 1    = "E"
nextx (-1) = "W"

main :: IO ()
main = do
    hSetBuffering stdout NoBuffering -- DO NOT REMOVE

    input_line <- getLine

    let input     = words input_line
        lightx    = read (head input) :: Int -- the X position of the light of power
        lighty    = read (input!!1) :: Int -- the Y position of the light of power
        initialtx = read (input!!2) :: Int -- Thor's starting X position
        initialty = read (input!!3) :: Int -- Thor's starting Y position

    loop lightx lighty initialtx initialty

loop :: Int -> Int -> Int -> Int -> IO ()
loop lightx lighty posx posy = do
    getLine

    let x = signum $ lightx - posx
        y = signum $ lighty - posy

    putStrLn $ nexty y ++ nextx x

    loop lightx lighty (posx + x) (posy + y)
