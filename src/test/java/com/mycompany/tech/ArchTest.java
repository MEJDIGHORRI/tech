package com.mycompany.tech;

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
            .importPackages("com.mycompany.tech");

        noClasses()
            .that()
            .resideInAnyPackage("com.mycompany.tech.service..")
            .or()
            .resideInAnyPackage("com.mycompany.tech.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mycompany.tech.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
