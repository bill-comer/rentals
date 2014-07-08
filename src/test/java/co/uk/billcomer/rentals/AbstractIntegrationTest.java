package co.uk.billcomer.rentals;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
            "classpath*:config/testContext.xml",
            "classpath*:config/test-dao.xml",
        })
        @DbUnitConfiguration(databaseConnection="rentals_dataSource")
        @TestExecutionListeners({
          DependencyInjectionTestExecutionListener.class,
          DirtiesContextTestExecutionListener.class,
          TransactionDbUnitTestExecutionListener.class, //<-- needed if using transactions otherwise use TransactionalTestExecutionListener.class
          DbUnitTestExecutionListener.class })
        @TransactionConfiguration(transactionManager="transactionManager", defaultRollback= true)
public abstract class AbstractIntegrationTest
{

}
