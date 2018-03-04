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

    public static final QAnimal animal = new QAnimal("animal");

    public final ListPath<Ficha, QFicha> fichas = this.<Ficha, QFicha>createList("fichas", Ficha.class, QFicha.class, PathInits.DIRECT2);

    public final NumberPath<Integer> flAtivo = createNumber("flAtivo", Integer.class);

    public final NumberPath<Integer> idAnimal = createNumber("idAnimal", Integer.class);

    public final StringPath nome = createString("nome");

    public QAnimal(String variable) {
        super(Animal.class, forVariable(variable));
    }

    public QAnimal(Path<? extends Animal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnimal(PathMetadata<?> metadata) {
        super(Animal.class, metadata);
    }

}

