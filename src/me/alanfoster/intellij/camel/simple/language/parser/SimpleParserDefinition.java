package me.alanfoster.intellij.camel.simple.language.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import me.alanfoster.intellij.camel.simple.language.SimpleLanguage;
import me.alanfoster.intellij.camel.simple.language.lexer.SimpleLexer;
import me.alanfoster.intellij.camel.simple.language.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE_TYPE = new IFileElementType(Language.<SimpleLanguage>findInstance(SimpleLanguage.class));

    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        // Our lexer is written using JFlex, which generates a lexer for us
        // We need to wrap it in an apapter so that IntelliJ can understand it!
        return new FlexAdapter(new SimpleLexer((Reader) null));
    }

    @Override
    public PsiParser createParser(Project project) {
        // Point it to the generated SimpleParser file from the JetBrains Grammar-kit
        return new SimpleParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE_TYPE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * The Simple language does not support comments.
     *
     * @return null
     */
    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        // TODO Add when supported
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode astNode) {
        // Our generated parser can create the Elements for us automatically
        return SimpleTypes.Factory.createElement(astNode);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new SimplePsiFile(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode2) {
        return SpaceRequirements.MAY;
    }
}
