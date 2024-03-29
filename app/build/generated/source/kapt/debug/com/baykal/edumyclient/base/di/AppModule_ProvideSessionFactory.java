// Generated by Dagger (https://dagger.dev).
package com.baykal.edumyclient.base.di;

import android.app.Application;
import com.baykal.edumyclient.base.preference.EdumySession;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ProvideSessionFactory implements Factory<EdumySession> {
  private final Provider<Application> applicationProvider;

  public AppModule_ProvideSessionFactory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public EdumySession get() {
    return provideSession(applicationProvider.get());
  }

  public static AppModule_ProvideSessionFactory create(Provider<Application> applicationProvider) {
    return new AppModule_ProvideSessionFactory(applicationProvider);
  }

  public static EdumySession provideSession(Application application) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSession(application));
  }
}
