package com.sls.tasker;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.sls.tasker");

        noClasses()
            .that()
            .resideInAnyPackage("com.sls.tasker.service..")
            .or()
            .resideInAnyPackage("com.sls.tasker.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.sls.tasker.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
