import dagger.Binds
import dagger.Module
import ru.savenkov.homework.Repository
import ru.savenkov.homework.data.RepositoryImpl
import javax.inject.Singleton

@Module
interface DataModule {


    @Singleton
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}