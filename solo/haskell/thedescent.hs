import           Control.Applicative ((<$>))
import           Control.Monad
import           System.IO

getLines :: Int -> IO [String]
getLines t = replicateM t getLine

getInts :: Int -> IO [Int]
getInts n = fmap read <$> getLines n

main :: IO ()
main = do
    hSetBuffering stdout NoBuffering -- DO NOT REMOVE
    loop

loop :: IO ()
loop = do
    input_line <- getLine
    let input = words input_line
        spacex = read $ head input
    mountains <- getInts 8
    let action =  if mountains !! spacex == maximum mountains
                  then "FIRE"
                  else "HOLD"
    putStrLn action
    loop
