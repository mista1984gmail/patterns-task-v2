package ru.clevertec.patterns.task.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import ru.clevertec.patterns.task.util.Constants;

@Aspect
public class LoadAndSaveCacheAspect {

	@Pointcut("@annotation(ru.clevertec.patterns.task.aspect.annotation.LoadCache)")
	public void loadCacheMethod() {
	}

	@Pointcut("@annotation(ru.clevertec.patterns.task.aspect.annotation.SaveCache)")
	public void saveCacheMethod() {
	}

	@Before(value = "loadCacheMethod()")
	public void loadCacheProfiling(JoinPoint jp) {
		Constants.CACHE.loadCache();
	}

	@After(value = "saveCacheMethod()")
	public void saveCacheProfiling(JoinPoint jp) {
		Constants.CACHE.saveCache();
	}

}
