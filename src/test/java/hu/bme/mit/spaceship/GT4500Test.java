package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;  

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class); 
    secondary = mock(TorpedoStore.class); 
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1); 
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(primary.getTorpedoCount())).thenReturn(true);
    when(secondary.fire(primary.getTorpedoCount())).thenReturn(true); 


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(primary.getTorpedoCount()); 
    verify(secondary, times(1)).fire(secondary.getTorpedoCount()); 
    assertEquals(true, result);
  }

}
