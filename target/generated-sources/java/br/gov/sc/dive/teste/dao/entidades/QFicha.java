package br.gov.sc.dive.teste.dao.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFicha is a Querydsl query type for Ficha
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFicha extends EntityPathBase<Ficha> {

    private static final long serialVersionUID = 546687281L;

    public static final QFicha ficha = new QFicha("ficha");

    public final ListPath<Animal, QAnimal> animais = this.<Animal, QAnimal>createList("animais", Animal.class, QAnimal.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> dtCadastro = createDateTime("dtCadastro", java.util.Date.class);

    public final NumberPath<Integer> flAtivo = createNumber("flAtivo", Integer.class);

    public final NumberPath<Integer> idFicha = createNumber("idFicha", Integer.class);

    public final StringPath observacao = createString("observacao");

    public QFicha(String variable) {
        super(Ficha.class, forVariable(variable));
    }

    public QFicha(Path<? extends Ficha> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFicha(PathMetadata<?> metadata) {
        super(Ficha.class, metadata);
    }

}

