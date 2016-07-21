package pl.sii.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Stream;

public class Crawler {

    private static boolean show = false;

    static int addPage( Integer page ) {
        Optional<Stream<String>> op = getStreamOfLinesForPage( page );
        StringBuilder sb = new StringBuilder();
        if ( op.isPresent() ) {
            op.get().forEach( sl -> {
                if ( sl.contains( "<br /><br />" ) ) {
                    show = true;
                }
                if ( sl.contains( "pages" ) ) {
                    show = false;
                }
                if ( show ) {
                    sb.append( sl );
                }
            } );
        }
        System.out.println(sb.toString());
        return 0;
    }

    private static Optional<Stream<String>> getStreamOfLinesForPage( Integer page ) {
        URL url;
        InputStreamReader isr = null;
        try {
            url = new URL( "http://www.raport-wsi.info/str.".concat( page.toString() ) );
            isr = new InputStreamReader( url.openStream(), "iso-8859-2" );
//          isr = url.openStream();  // throws an IOException
            BufferedReader in = new BufferedReader( isr );
            //br returns as stream and convert it into a List
            return Optional.of( in.lines() );
        } catch ( MalformedURLException mue ) {
            mue.printStackTrace();
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        } finally {
//            try {
//                isr.close();
//            } catch (IOException ioe) {
//                // nothing to see here
//            }
        }
        return Optional.empty();
    }

    public static boolean isStart( Stream<String> l ) {
        if ( l.findFirst().get().contains( "<br /><br />" ) ) {
            show = true;
        }
        return show;
    }

    public static boolean isEnd( Stream<String> l ) {
        if ( l.findFirst().get().contains( "pages" ) ) {
            show = false;
        }
        return show;
    }

}