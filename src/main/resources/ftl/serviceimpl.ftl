package ${package}.${directory};

import ${package}.entity.${class};
import ${package}.service.${class}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ${class}ServiceImpl implements ${class}Service {

    @Autowired


	void save(${class} ${classObj});

}