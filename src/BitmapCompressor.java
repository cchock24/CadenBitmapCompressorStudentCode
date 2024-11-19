/******************************************************************************
 *  Compilation:  javac BitmapCompressor.java
 *  Execution:    java BitmapCompressor - < input.bin   (compress)
 *  Execution:    java BitmapCompressor + < input.bin   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   q32x48.bin
 *                q64x96.bin
 *                mystery.bin
 *
 *  Compress or expand binary input from standard input.
 *
 *  % java DumpBinary 0 < mystery.bin
 *  8000 bits
 *
 *  % java BitmapCompressor - < mystery.bin | java DumpBinary 0
 *  1240 bits
 ******************************************************************************/

/**
 *  The {@code BitmapCompressor} class provides static methods for compressing
 *  and expanding a binary bitmap input.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 *  @author Caden Chock
 */
public class BitmapCompressor {

    /**
     * Reads a sequence of bits from standard input, compresses them,
     * and writes the results to standard output.
     */
    public static void compress() {
        // TODO: complete compress()
        boolean first = BinaryStdIn.readBoolean();
        int counter = 1;
        if(first){
            BinaryStdOut.write(1, 8);
        }
        else{
            BinaryStdOut.write(0,8);
        }
        while(!BinaryStdIn.isEmpty()){
            // Add to Counter
            if(BinaryStdIn.readBoolean() == first){
                counter++;
            }
            // Add number to Compressed File change current
            else{
                BinaryStdOut.write(counter);
                first = !first;
                counter = 1;
            }
        }
        BinaryStdOut.write(counter);
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits from standard input, decodes it,
     * and writes the results to standard output.
     */
    public static void expand() {

        // TODO: complete expand()
        int first = BinaryStdIn.readInt(8);
        int num = 0b0;
        if(first == 1){
            num = 0b1;
        }
        while(!BinaryStdIn.isEmpty()){
            int repeat = BinaryStdIn.readInt();
            for(int i = 0; i < repeat; i++){
                BinaryStdOut.write(num,1);
            }
            if(num == 0b0){
                num = 0b1;
            }
            else{
                num = 0b0;
            }
        }
        BinaryStdOut.close();
    }

    /**
     * When executed at the command-line, run {@code compress()} if the command-line
     * argument is "-" and {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}