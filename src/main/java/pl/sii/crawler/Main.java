package pl.sii.crawler;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) throws IOException, SolrServerException {

        IntStream.iterate( 2, i -> i + 1 )
                .map( Crawler::addPage )
                .limit( 162 )
                .reduce( 0 , (p, n) -> p + n );

    }

}
