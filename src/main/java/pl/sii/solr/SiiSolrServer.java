package pl.sii.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.util.Objects;

/**
 * Created by pkura on 2016-07-12.
 */

class SiiSolrServer {
    private static HttpSolrClient solrClient;

    static HttpSolrClient getSolrClient() {
        if ( Objects.isNull( solrClient ) ) {
            solrClient = new HttpSolrClient( "http://localhost:8983/solr/demo" );
        }
        return solrClient;
    }
}
