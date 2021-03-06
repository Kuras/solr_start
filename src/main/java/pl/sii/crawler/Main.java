package pl.sii.crawler;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import pl.sii.solr.SiiSolrServer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException, SolrServerException {

        System.out.println("Start index");
        HttpSolrClient server = SiiSolrServer.getSolrClient("wsi");

        IntStream.iterate( 2, i -> i + 1 )
                .map( Crawler::addPage )
                .limit( 162 )
                .reduce( 0 , (p, n) -> p + n );

        Collection<SolrInputDocument> docs = Crawler.getStreamOfPages()
                .map( page -> {
                    SolrInputDocument doc = new SolrInputDocument();
                    doc.addField( "page", page.getNumber(), 1.0f );
                    doc.addField( "content", page.getContent() );
                    return doc;
                } )
                .collect( Collectors.toList() );

        server.add( docs );
        server.commit();
        server.close();
        System.out.println("End index");
    }

}
