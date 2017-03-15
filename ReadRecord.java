/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: Jeremy Phillips and Jordan Nordh
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  opens an ObjectInputStream using a FileInputStream
     */
    private void readObjectsFromFile(String name)
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream(name));
        } catch (IOException ioException) {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several records.
         * Loop should terminate when an EOFException is thrown.
         */
        try
        {
            while ( true )
            {
                record = (MusicRecord) input.readObject();
                System.out.println(record.getYear() + " " + record.getSongName() + " " 
                		+ record.getSingerName() + " " + record.getPurchasePrice());
            }
        } catch(EOFException e){
            System.out.println( "EOF reached.");
        } catch (ClassNotFoundException e) {
            System.err.println( "Error: Class not found." );
		} catch (IOException e) {
            System.err.println( "Error: IO Exception." );
		}
    }
    
    
    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("allSongs.ser");
    }
}
