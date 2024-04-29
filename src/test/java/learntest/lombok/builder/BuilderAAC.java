package learntest.lombok.builder;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BuilderAAC {

	@Test
	@DisplayName("@Builder 의 build 호출 시 All arguments 생성자가 호출된다.")
	void test(){
		// Test # Given
		assertThat(constructorCalled).isFalse();

		// Test # When
		Clazz.builder().build();

		// Test # Then
		assertThat(constructorCalled)
				.as("Builder 를 통해 build 하면 AllArgumentsConstructor 가 호출된다!!")
				.isTrue();
	}


	static boolean constructorCalled = false;
	@Builder
	@NoArgsConstructor
	public static class Clazz{
		private String param1;
		private Integer param2;

		public Clazz(String param1, Integer param2) {
			this.param1 = param1;
			this.param2 = param2;
			constructorCalled = true;
		}
	}
}
