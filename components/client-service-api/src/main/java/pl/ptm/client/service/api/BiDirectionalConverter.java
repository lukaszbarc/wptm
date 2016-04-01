package pl.ptm.client.service.api;

/**
 * Created by jbogacz on 2016-04-01.
 */
public interface BiDirectionalConverter<LEFT, RIGHT> {

    RIGHT toRight(LEFT left);

    LEFT toLeft(RIGHT right);
}
