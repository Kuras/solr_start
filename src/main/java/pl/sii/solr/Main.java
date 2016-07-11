package pl.sii.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pkura on 2016-07-11.
 */
public class Main {

    public static void main( String[] args ) throws IOException, SolrServerException {
        System.out.println("Solr");
        HttpSolrClient server = new HttpSolrClient( "http://localhost:8983/solr/demo" );

        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );

        server.add( docs );
        server.commit();

    }
}
