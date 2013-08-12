//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.*;
import java.util.List;


/**
 * @author Alan
 */
//@SubTag("AbstractClass")
public interface ExpressionNode extends ProcessorDefinition, DomElement  {
    
        
        
        
        @SubTagsList({"ognl", "spel", "header", "sql", "constant",
						"el", "expressionDefinition", "jxpath", "simple", "php",
						"javaScript", "language", "python", "ruby", "method",
						"xpath", "tokenize", "property", "ref", "xquery",
						"groovy", "mvel"})
                ExpressionDefinition getExpression();

        
        
                                            @SubTagList("xquery")
                XQueryExpression getXquery();
                                            @SubTagList("expressionDefinition")
                ExpressionDefinition getExpressionDefinition();
                                            @SubTagList("simple")
                SimpleExpression getSimple();
                                            @SubTagList("constant")
                ConstantExpression getConstant();
                                            @SubTagList("tokenize")
                TokenizerExpression getTokenize();
                                            @SubTagList("javaScript")
                JavaScriptExpression getJavaScript();
                                            @SubTagList("method")
                MethodCallExpression getMethod();
                                            @SubTagList("ruby")
                RubyExpression getRuby();
                                            @SubTagList("groovy")
                GroovyExpression getGroovy();
                                            @SubTagList("xpath")
                XPathExpression getXpath();
                                            @SubTagList("jxpath")
                JXPathExpression getJxpath();
                                            @SubTagList("el")
                ELExpression getEl();
                                            @SubTagList("mvel")
                MvelExpression getMvel();
                                            @SubTagList("sql")
                SqlExpression getSql();
                                            @SubTagList("ref")
                RefExpression getRef();
                                            @SubTagList("python")
                PythonExpression getPython();
                                            @SubTagList("spel")
                SpELExpression getSpel();
                                            @SubTagList("header")
                HeaderExpression getHeader();
                                            @SubTagList("php")
                PhpExpression getPhp();
                                            @SubTagList("property")
                PropertyExpression getProperty();
                                            @SubTagList("ognl")
                OgnlExpression getOgnl();
                                            @SubTagList("language")
                LanguageExpression getLanguage();
                            
        
        
        @SubTagsList({"setOutHeader", "bean", "log", "interceptToEndpoint", "choice",
						"interceptFrom", "transacted", "onCompletion", "setBody", "rollback",
						"throwException", "doTry", "setExchangePattern", "stop", "throttle",
						"loop", "removeHeader", "threads", "intercept", "split",
						"delay", "setProperty", "dynamicRouter", "doCatch", "enrich",
						"when", "idempotentConsumer", "loadBalance", "setHeader", "filter",
						"resequence", "setFaultBody", "routingSlip", "unmarshal", "aggregate",
						"wireTap", "marshal", "aop", "pipeline", "pollEnrich",
						"inOut", "removeProperty", "policy", "transform", "sample",
						"process", "validate", "multicast", "otherwise", "sort",
						"convertBodyTo", "inOnly", "recipientList", "onException", "to",
						"removeHeaders", "doFinally", "route"})
                List<ProcessDefinition> getOutputs();

        
        
                                            @SubTagList("transform")
                List<TransformDefinition> getTransforms();
                                            @SubTagList("dynamicRouter")
                List<DynamicRouterDefinition> getDynamicRouters();
                                            @SubTagList("setHeader")
                List<SetHeaderDefinition> getSetHeaders();
                                            @SubTagList("stop")
                List<StopDefinition> getStops();
                                            @SubTagList("onCompletion")
                List<OnCompletionDefinition> getOnCompletions();
                                            @SubTagList("aop")
                List<AOPDefinition> getAops();
                                            @SubTagList("pipeline")
                List<PipelineDefinition> getPipelines();
                                            @SubTagList("inOut")
                List<InOutDefinition> getInOuts();
                                            @SubTagList("split")
                List<SplitDefinition> getSplits();
                                            @SubTagList("wireTap")
                List<WireTapDefinition> getWireTaps();
                                            @SubTagList("interceptToEndpoint")
                List<InterceptSendToEndpointDefinition> getInterceptToEndpoints();
                                            @SubTagList("setProperty")
                List<SetPropertyDefinition> getSetPropertys();
                                            @SubTagList("route")
                List<RouteDefinition> getRoutes();
                                            @SubTagList("removeHeader")
                List<RemoveHeaderDefinition> getRemoveHeaders();
                                            @SubTagList("setBody")
                List<SetBodyDefinition> getSetBodys();
                                            @SubTagList("delay")
                List<DelayDefinition> getDelays();
                                            @SubTagList("removeHeaders")
                List<RemoveHeadersDefinition> getRemoveHeaderss();
                                            @SubTagList("resequence")
                List<ResequenceDefinition> getResequences();
                                            @SubTagList("setExchangePattern")
                List<SetExchangePatternDefinition> getSetExchangePatterns();
                                            @SubTagList("to")
                List<ToDefinition> getTos();
                                            @SubTagList("doCatch")
                List<CatchDefinition> getDoCatchs();
                                            @SubTagList("recipientList")
                List<RecipientListDefinition> getRecipientLists();
                                            @SubTagList("idempotentConsumer")
                List<IdempotentConsumerDefinition> getIdempotentConsumers();
                                            @SubTagList("log")
                List<LogDefinition> getLogs();
                                            @SubTagList("routingSlip")
                List<RoutingSlipDefinition> getRoutingSlips();
                                            @SubTagList("multicast")
                List<MulticastDefinition> getMulticasts();
                                            @SubTagList("doTry")
                List<TryDefinition> getDoTrys();
                                            @SubTagList("interceptFrom")
                List<InterceptFromDefinition> getInterceptFroms();
                                            @SubTagList("throwException")
                List<ThrowExceptionDefinition> getThrowExceptions();
                                            @SubTagList("loadBalance")
                List<LoadBalanceDefinition> getLoadBalances();
                                            @SubTagList("intercept")
                List<InterceptDefinition> getIntercepts();
                                            @SubTagList("removeProperty")
                List<RemovePropertyDefinition> getRemovePropertys();
                                            @SubTagList("onException")
                List<OnExceptionDefinition> getOnExceptions();
                                            @SubTagList("convertBodyTo")
                List<ConvertBodyDefinition> getConvertBodyTos();
                                            @SubTagList("policy")
                List<PolicyDefinition> getPolicys();
                                            @SubTagList("enrich")
                List<EnrichDefinition> getEnrichs();
                                            @SubTagList("aggregate")
                List<AggregateDefinition> getAggregates();
                                            @SubTagList("loop")
                List<LoopDefinition> getLoops();
                                            @SubTagList("inOnly")
                List<InOnlyDefinition> getInOnlys();
                                            @SubTagList("filter")
                List<FilterDefinition> getFilters();
                                            @SubTagList("process")
                List<ProcessDefinition> getProcesss();
                                            @SubTagList("throttle")
                List<ThrottleDefinition> getThrottles();
                                            @SubTagList("marshal")
                List<MarshalDefinition> getMarshals();
                                            @SubTagList("otherwise")
                List<OtherwiseDefinition> getOtherwises();
                                            @SubTagList("threads")
                List<ThreadsDefinition> getThreadss();
                                            @SubTagList("sort")
                List<SortDefinition> getSorts();
                                            @SubTagList("doFinally")
                List<FinallyDefinition> getDoFinallys();
                                            @SubTagList("sample")
                List<SamplingDefinition> getSamples();
                                            @SubTagList("choice")
                List<ChoiceDefinition> getChoices();
                                            @SubTagList("pollEnrich")
                List<PollEnrichDefinition> getPollEnrichs();
                                            @SubTagList("unmarshal")
                List<UnmarshalDefinition> getUnmarshals();
                                            @SubTagList("rollback")
                List<RollbackDefinition> getRollbacks();
                                            @SubTagList("bean")
                List<BeanDefinition> getBeans();
                                            @SubTagList("setOutHeader")
                List<SetOutHeaderDefinition> getSetOutHeaders();
                                            @SubTagList("transacted")
                List<TransactedDefinition> getTransacteds();
                                            @SubTagList("setFaultBody")
                List<SetFaultBodyDefinition> getSetFaultBodys();
                                            @SubTagList("when")
                List<WhenDefinition> getWhens();
                                            @SubTagList("validate")
                List<ValidateDefinition> getValidates();
                        
    }