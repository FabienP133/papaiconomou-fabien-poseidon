import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

    @Mock
    private BidListRepository repo;
    @InjectMocks
    private BidListService service;

    @Test
    void findById_shouldThrow_whenNotFound() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.findById(1));
    }

    @Test
    void create_shouldSave() {
        BidList b = new BidList();
        b.setAccount("acc");
        b.setType("type");

        when(repo.save(any(BidList.class))).thenReturn(b);

        BidList saved = service.create(b);
        assertNotNull(saved);
        verify(repo).save(b);
    }
}