Useful References
==================

Find Module for PsiElement

```java
Module moduleForDomElement = ModuleUtil.findModuleForPsiElement(domElement.getXmlElement());
Module moduleForPsiFile = ModuleUtil.findModuleForPsiElement(psiFile);
```

Find virtual files by type

```java
Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
        FileTypeIndex.NAME, StdFileTypes.JAVA, GlobalSearchScope.moduleScope(moduleForPsiElement));
```

Get JavaPsiFile with Module scope from VirtualFile

```java
Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
        FileTypeIndex.NAME, StdFileTypes.JAVA, GlobalSearchScope.moduleScope(module));

VirtualFile virtualFile = virtualFiles.iterator().next();

PsiFile file = PsiManager.getInstance(project)
             .findFile(virtualFile);

PsiJavaFile javaFile = (PsiJavaFile) file;
```

Find Java class by name, with specified scope

```java
final PsiClass psiClass = JavaPsiFacade.getInstance(module.getProject())
        .findClass("fully.qualified.name.Foo", module.getModuleWithDependenciesAndLibrariesScope(true));
```