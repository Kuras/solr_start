package pl.sii.efficient;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by pkura on 2016-07-20.
 */
public class MainEfficient {
    public static void main( String[] args ) {
        Observable.from( new String[]{"a","b","c"} ).subscribe( s -> System.out.println(s.concat( "-" )));

        getObservable(1000).subscribe( new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError( Throwable e ) {

            }

            @Override
            public void onNext( Object o ) {
                System.out.println(o);
            }
        } );
    }

    private static Observable<?> getObservable( Integer i ) {
        return Observable.create( subscriber -> {
            for ( int j = 0; j < i; j+=50 ) {
                subscriber.onNext( j );
            }
        } );
    }
}
