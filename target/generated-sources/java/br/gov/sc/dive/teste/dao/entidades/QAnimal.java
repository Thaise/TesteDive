package br.gov.sc.dive.teste.dao.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAnimal is a Querydsl query type for Animal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAnimal extends EntityPathBase<Animal> {

    private static final long serialVersionUID = -370907964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnimal animal = new QAnimal("animal");

    public final QFicha ficha;

    public final NumberPath<Integer> flAtivo = createNumber("flAtivo", Integer.class);

    public final NumberPath<Integer> idAnimal = createNumber("idAnimal", Integer.class);

    public final StringPath nome = createString("nome");

    public QAnimal(String variable) {
        this(Animal.class, forVariable(variable), INITS);
    }

    public QAnimal(Path<? extends Animal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAnimal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAnimal(PathMetadata<?> metadata, PathInits inits) {
        this(Animal.class, metadata, inits);
    }

    public QAnimal(Class<? extends Animal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ficha = inits.isInitialized("ficha") ? new QFicha(forProperty("ficha")) : null;
    }

}

