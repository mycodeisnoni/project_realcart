## Python code convention
원문 : https://luavis.me/python/python-convention


## 문서 소개
이 문서는 기본 파이썬 배포판의 표준 라이브러리를 구성하는 파이썬 코드 의 코딩 규칙(coding convention)을 설명한다. 파이썬의 C 구현에 대한 스타일 가이드라인을 설명하는 PEP[1]도 참고하길 바란다.

이 문서와 PEP 257(Docstring 규칙)은 귀도가 작성한 파이썬 스타일 가이 드에 관한 짧은 글에 바탕을 두고 있으며, 후에 배리의 스타일 가이드[2] 에서 일부 내용을 추가하였다.

멍청하게 일관성을 고집하는 것은 소인배의 발상이다 (A Foolish Consistency is the Hobgoblin of Little Minds)

귀도의 중요한 통찰 중 하나는 프로그램 코드가 작성되는 횟수보다는 사람 에게 읽히는 횟수가 더 많다는 사실이다. 이 문서에서 제공하는 가이드라 인은 코드의 가독성을 향상시키고 다양한 파이썬 코드에 일관성을 부여하 고자하는 의도를 갖고 있다. PEP 20에서 언급하듯, “가독성은 중요하다.”

스타일 가이드는 일관성을 다룬다. 이 스타일 가이드에서 다루는 일관성 은 중요한 내용이다. 프로젝트에서의 일관성은 중요하지만, 특정한 모듈 혹은 함수 내에서의 일관성은 정말 중요하다.

하지만 무엇보다 중요한 것은, 어느 순간에 일관성을 버려야 할 지 알아야 한다는 것이다 - 어떤 순간에는 스타일 가이드를 적용하지 않을 수 있다. 의심이 든다면 최선을 다해 판단해라. 다른 예제들을 살펴보고 어떤 것이 가장 좋아 보이는지 결정하라. 그리고 주저하지 말고 질문하라!

특정한 규칙을 무시해도 괜찮은 두 가지 경우를 살펴보자:

기존의 규칙을 따르는 코드를 읽는데 익숙한 사람조차 해당 규칙을 적 용한 코드의 가독성이 나빠진다고 판단할 경우.

(아마 기존 관습 때문에) 일관성을 잃은 기존 코드를 갈아엎어서 코드 의 일관성을 확립하고자 하는 경우. 이는 기존 코드의 지저분한 부분 들을 (진정한 XP 스타일로) 정리할 수 있는 기회이기도 하다.

## 코드 레이아웃
들여쓰기
4개의 공백을 들여쓰기의 단위로 사용하라.

정말 오래된 코드를 엉망으로 만들고 싶지 않은 경우라면, 8개의 공백을 계속 사용해도 된다.

여러 줄에 걸쳐 코드를 작성할 경우 파이썬의 괄호, 중괄호(‘{‘), 대괄호 (‘[‘)처럼 암시적으로 여러 줄을 결합하는 기능을 사용하거나, 들여쓰기 (hanging indent)*를 사용하여 코드의 요소들을 수직으로 정렬해야 한다. 들여쓰기를 사용하려면 다음과 같은 사항을 고려해야 한다; 첫 번째 줄에 인수가 오면 안 되며, 들여쓰기를 계속 사용할 경우 행이 계속된다는 사실 을 분명히 파악할 수 있도록 사용해야 한다.

hanging indent; 문장 중간에 들여쓰기를 사용하는 형식.
좋은 예::

# 구분자를 사용하여 정렬하기
foo = long_function_name(var_one, var_two,
                         var_three, var_four)

# 나머지 코드와의 구별을 위해 추가적인 들여쓰기를 사용했다.
def long_function_name(
        var_one, var_two, var_three,
        var_four):
    print(var_one)
나쁜 예::

# 수직 정렬을 하지 않는다면, 인수를 첫 번째 줄에 사용하지 마라.
foo = long_function_name(var_one, var_two,
    var_three, var_four)

# 다음 행과 구별이 안 되므로 추가적인 들여쓰기가 필요하다.
def long_function_name(
    var_one, var_two, var_three,
    var_four):
    print(var_one)
선택사항::

# 부가적인 들여쓰기는 딱히 사용하지 않아도 된다.
foo = long_function_name(
  var_one, var_two,
  var_three, var_four)
여러 줄에 걸친 괄호/중괄호/대괄호를 닫을 경우 리스트의 마지막 항목에 맞추어 다음 줄에 닫는 표시를 할 수 있다. 다음 코드를 보자:

my_list = [
    1, 2, 3,
    4, 5, 6,
    ]
result = some_function_that_takes_arguments(
    'a', 'b', 'c',
    'd', 'e', 'f',
    )
혹은 이렇게 여러 줄에 걸친 코드의 첫 번째 문자 위치에 닫는 표시를 넣 을 수도 있다. 다음 코드를 보자:

my_list = [
    1, 2, 3,
    4, 5, 6,
]
result = some_function_that_takes_arguments(
    'a', 'b', 'c',
    'd', 'e', 'f',
)

## 탭이냐 공백이냐
절대 탭 문자와 공백 문자를 섞어 쓰지 마라.

파이썬에서 들여쓰기에 주로 사용되는 방식은 공백 문자만 사용하는 것이 다. 두 번째로 많이 쓰이는 방식은 탭 문자만 사용하는 것이다. 탭 문 자와 공백 문자가 섞인 코드는 반드시 공백 문자만 사용한 코드로 변환해 야 한다. -t 옵션을 사용하여 파이썬 명령줄 인터프리터를 호출하면 탭 문자와 공백 문자가 섞인 코드가 있을 경우 인터프리터가 경고 메시지 를 표시한다. -tt 옵션을 사용하면 이 경고 메시지가 에러 메시지로 표시된다. 위 옵션들을 사용하기를 권한다.

새로운 프로젝트를 만들 경우 탭 문자 대신 공백 문자만을 사용하도록 하 자. 대부분의 편집기에서는 이러한 작업을 쉽게 처리할 수 있다.

## 한 행의 최대 길이
한 행에는 최대 79글자까지만 넣도록 한다.

세상에는 한 행에 80글자까지만 표시할 수 있는 장비들이 아직 많이 남아 있다. 게다가, 창 하나에 80글자만 표시하도록 강제할 경우 여러 창을 열어서 늘어놓고 작업할 수 있다. 80글자 제한이 있는 장치에서 자동 줄 바꿈(wrapping)이 일어날 경우 코드의 시각적인 구조가 망가지며, 이렇게 되면 코드를 이해하기 어려워진다. 그러므로 코드 한 행은 최대 79글자 로 제한하도록 하자. 텍스트가 길게 이어지는 경우(docstring 혹은 주석 문)에는 72글자로 제한하는 것을 권장한다.

글자가 많은 행에 줄바꿈을 적용할 경우 좋은 방법은 괄호, 중괄호, 대괄 호 등의 내부에 여러 행의 코드를 적는 것이다. 파이썬에서 이들 코드는 암시적으로 계속 이어지는 것으로 처리된다. 괄호를 사용한 줄바꿈을 적 용하여 긴 코드를 여러 행의 코드로 바꿀 수 있다. 이 방법은 줄이 계속 됨을 나타내는 백슬래시(‘') 문자를 사용하기 전에 사용해야 한다. 이 진 연산자에서 줄바꿈을 할 경우 줄바꿈하기 좋은 곳은 연산자의 다음 위치이다(이전 위치가 아니다). 몇 가지 예를 살펴보자::

class Rectangle(Blob):

    def __init__(self, width, height,
                 color='black', emphasis=None, highlight=0):
        if (width == 0 and height == 0 and
            color == 'red' and emphasis == 'strong' or
            highlight > 100):
            raise ValueError("sorry, you lose")
        if width == 0 and height == 0 and (color == 'red' or
                                           emphasis is None):
            raise ValueError("I don't think so -- values are %s, %s" %
                             (width, height))
        Blob.__init__(self, width, height,
                      color, emphasis, highlight)

## 빈 줄
최상위 수준 함수와 클래스 정의는 두 줄을 띄워서 구분한다.

클래스의 메서드 정의는 한 줄을 띄워서 구분한다.

서로 연관이 있는 함수들의 묶음을 구분하기 위해 빈 줄을 추가로 사용할 수 있다. 반대로 한 줄짜리 구현 코드가 뭉쳐 있을 경우 이러한 빈 줄을 생략할 수도 있다. (예: 일련의 미구현, 임시 코드)

함수 내에서 논리적인 단위를 드러내기 위해 빈 줄을 사용하라. (하지만 아껴서 사용하자)

파이썬은 컨트롤-L(^L) 폼 피드 문자를 공백으로 취급한다; 많은 프로그 램이 이 문자를 페이지 구분자로 취급하며, 파일 내에서 서로 연관성이 있는 단락을 페이지별로 구분하는데 이 문자를 사용할 수 있다. 특정 편집기나 웹 기반의 코드 뷰어는 컨트롤-L을 폼 피드 문자로 인식하지 않을 수도 있으며, 이 경우 폼 피드를 대신하는 다른 문자가 컨트롤-L 문자의 위치에 표시될 것이다.

## 인코딩 (PEP 263)
파이썬 배포본 내의 코드는 항상 ASCII 혹은 Latin-1 인코딩(ISO-8859-1 이라고도 한다)을 사용해야 한다. 파이썬 3.0 이상 버전에서는 Latin-1 대신 UTF-8을 권장한다(PEP 3120을 참고하라).

ASCII 인코딩을 사용하는 파일은 코딩 쿠키를 포함하면 안 된다. 주석 혹 은 docstring에서 Latin-1 문자로 기록된 문서 작성자의 이름을 언급할 필요가 있을 때에만 Latin-1(혹은 UTF-8)을 사용한다. 혹은, 비 ASCII 데이터를 문자 상수에 넣으려면 \x, \u, \U 등의 이스케이프 문자를 사용하길 권한다.

파이썬 3.0 이상 버전에서는 표준 라이브러리에 대해 다음과 같은 정책을 취하고 있다(PEP 3131을 참고): 파이썬 표준 라이브러리에서는 ASCII 문 자만으로 구성된 식별자를 사용해야 하며, 모든 경우에 적절한 영어 단어 를 사용하는 것이 좋다(많은 경우, 어어라고 할 수 없는 약어와 기술 용 어가 쓰이곤 한다). 또한, 문자 상수와 주석 또한 ASCII로 작성되어야 한다. 유일한 예외는 (a) 비 ASCII 문자가 포함된 기능을 테스트하는 테스트 케이스 (b) 작성자의 이름. 라틴 알파벳 이외의 문자를 사용하는 이름을 가진 작 성자는 자신의 이름에 대한 라틴어 음역을 같이 제공해야 한다.

전세계인이 참여 중인 오픈 소스 프로젝트들은 이와 비슷한 정책을 따르 도록 하고 있다.

## 임포트
임포트는 보통 행으로 구분되어야 한다.

예를 들면::
좋음: import os import sys
나쁨: import sys, os

하지만 이렇게 사용하는 것은 괜찮다::
from subprocess import Popen, PIPE
임포트 구문은 항상 파일의 최상단에 위치해야 하며, 모듈의 주석문과 docstring의 바로 다음, 그리고 모듈의 전역 변수와 상수 바로 이전에 위치한다.

임포트 구문은 다음 순서에 따라 그룹별로 기술한다::
표준 라이브러리
관련이 있는 서드파티 라이브러리
로컬 어플리케이션/자체 라이브러리
각 그룹 사이에는 빈 줄 하나를 넣어야 한다.

임포트 구문 다음에는 __all__ 명세를 넣는다.

인접(relative) 경로를 사용하여 내부 패키지를 임포트하지 않도록 한 다. 항상 절대 패키지 경로를 사용하여 임포트를 하도록 하자. 현재 파이썬 2.5에 PEP 328이 완전히 구현되어 있으나, 명시적 인접 임포트 (explicit relative import)는 여전히 권장되지 않는다; 절대 임포트 (역주: 절대 경로를 사용한 임포트)는 이식성이 높으며, 일반적으로 읽기 더 쉽다.

클래스를 포함하고 있는 모듈에서 클래스를 임포트할 때, 보통은 다음 과 같이 기술한다::
from myclass import MyClass
from foo.bar.yourclass import YourClass
만약 이름이 이미 선언된 로컬 이름과 충돌할 경우, 다음과 같이 기술 한다::

import myclass
import foo.bar.yourclass
그런 다음 “myclass.MyClass”, “foo.bar.yourclass.YourClass” 같은 식으로 클래스를 사용하면 된다.

표현식, 구문에서의 공백 문자
눈엣가시*
다음과 같은 상황에서는 여분의 공백 문자를 사용하지 않는다:

괄호, 중괄호, 대괄호 내부에 연결되는 부분::

좋음: spam(ham[1], {eggs: 2})
나쁨: spam( ham[ 1 ], { eggs: 2 } )
콤마, 세미콜론, 콜론의 이전 위치::

좋음: if x == 4: print x, y; x, y = y, x
나쁨: if x == 4 : print x , y ; x , y = y , x
함수 호출시 인수 목록이 시작되는 괄호의 바로 이전 위치::

좋음: spam(1)
나쁨: spam (1)
인덱싱 혹은 슬라이싱이 시작되는 괄호의 바로 이전 위치::

좋음: dict['key'] = list[index]
나쁨: dict ['key'] = list [index]
할당(혹은 기타 다른) 연산자 주변에 한 개를 초과하는 공백 문자가 있는 경우::

좋음::
x = 1
y = 2
long_variable = 3

나쁨::
x             = 1
y             = 2
long_variable = 3
Pet Peeves. ‘자꾸 눈에 거슬리는 것’을 이르는 관용어.

## 기타 권고사항
항상 이진 연산자의 주위에는 한 개의 공백을 넣는다: 할당 (=), 증감 할당 (+=, -= 등.), 비교 (==, <, >, !=, <>, <=, >=, in, not in, is, is not), 부울 연산 (and, or, not).

우선 순위가 서로 다른 연산자를 함께 사용할 경우, 우선 순위가 낮은 연산자 주위에 공백을 넣을지 고려해보자. 하지만 이 경우에도 한 개 를 초과하는 공백 문자를 사용하면 안 되며, 이진 연산자의 양쪽을 똑 같은 방식으로 기술하도록 한다.

좋음::
i = i + 1
submitted += 1
x = x*2 - 1
hypot2 = x*x + y*y
c = (a+b) * (a-b)

나쁨::
i=i+1
submitted +=1
x = x * 2 - 1
hypot2 = x * x + y * y
c = (a + b) * (a - b)
키워드 인수 혹은 기본 매개변수 값을 나타내는 경우에는 = 기호 주위에 공백을 넣지 않는다.

좋음::
def complex(real, imag=0.0):
    return magic(r=real, i=imag)

나쁨::
def complex(real, imag = 0.0):
    return magic(r = real, i = imag)
복합 구문(Compound statements, 여러 구문이 한 줄에 있는 것)은 일반 적으로 권장되지 않는다.

좋음::
if foo == 'blah':
    do_blah_thing()
do_one()
do_two()
do_three()
Rather not::

if foo == 'blah': do_blah_thing()
do_one(); do_two(); do_three()
짧은 if/for/while 구문을 한 줄에 넣는 것은 괜찮지만, 여러 절을 가 진 구문은 절대 한 줄에 기술하지 않는다. 또한 한 줄에 여러 구문을 기술하면서 줄바꿈을 하는 것도 피해야 한다.

그런대로 괜찮음::

if foo == 'blah': do_blah_thing()
for x in lst: total += x
while t < 10: t = delay()
절대 안됨::

if foo == 'blah': do_blah_thing()
else: do_non_blah_thing()

try: something()
finally: cleanup()

do_one(); do_two(); do_three(long, argument,
                             list, like, this)

if foo == 'blah': one(); two(); three()
주석문
코드의 내용과 동떨어진 주석이 있는 것보다는 아예 주석이 없는 편이 낫 다. 주석은 항상 코드의 변경에 맞추어 최신의 상태를 유지하도록 한다.

주석은 완결된 문장 형태로 작성한다. 한 구절 혹은 문장으로 이루어진 주석을 작성할 경우, 첫 번째 단어를 대문자로 기술한다. 예외적으로, 이 첫 번째 단어가 소문자로 된 식별자일 경우에는 그대로 소문자로 기술 한다(이렇게 식별자를 주석에 사용할 경우에는 절대 소문자/대문자를 바 꾸지 말아야 한다).

주석문이 짧을 경우 마지막에 오는 마침표는 생략할 수 있다. 블록 형태 의 주석문은 일반적으로 완결된 문장들로 구성된 문단들이며, 각각의 문 단은 마침표로 끝나야 한다.

문장의 마침표 다음에는 두 개의 공백을 넣는다.

영문으로 작성할 경우 Strunk와 White*의 스타일을 적용한다.

비영어권 국가의 파이썬 코더의 경우, 다른 나라의 개발자가 현재 당신이 작성하는 코드를 읽게 될 일이 전혀 없으리란 확신이 들지 않는다면, 부 디 영문으로 주석을 작성해주길 바란다.

영어 작문에 관한 책인 ‘The Elements of Style’의 저자.
블록 주석문
블록 주석문은 일반적으로 블록 주석문 이후에 기술되는 일부(혹은 전체) 코드와 관련된 내용이며, 해당 코드와 같은 수준만큼 들여쓰게 된다. 블 록 주석문의 각 행은 #문자와 한 개의 공백으로 시작된다(코멘트 내 부에 들여쓰기된 텍스트가 있을 경우는 제외)

블록 주석문 내부의 문단들은 한 개의 # 문자를 포함한 빈 줄을 넣어 구분한다.

인라인 주석문
인라인 주석문은 아껴서 사용한다.

인라인 주석문은 코드와 동일한 행에 기술되는 주석문이다. 인라인 주석 문은 코드에서 최소한 두 개 이상의 공백으로 분리되어 있어야 한다. 주 석문은 # 문자와 한 개의 공백으로 시작해야 한다.

뻔한 내용의 인라인 주석문은 불필요하며, 사실 방해물이라 할 수 있다. 이런 주석문은 피하자::

x = x + 1                 # x의 증가
하지만 이런 주석문은 유용할 때가 있다::

x = x + 1                 # 경계값에 대한 보상
문서화 문자열
좋은 문서화 문자열(Documentation String, docstring이라고도 한다)을 작성하기 위한 규칙으로 PEP 257가 널리 알려져 있다.

public으로 정의된 모든 모듈, 함수, 클래스, 메서드에 대해 docstring 을 작성한다. public으로 정의되지 않은 메서드에는 docstring을 작성 할 필요가 없다. 하지만 해당 메서드가 무슨 일을 하는지 설명하는 주 석은 달아야 한다. 이러한 주석은 def가 있는 행 바로 다음 행에 작성한다.

PEP 257은 좋은 docstring 작성 규칙에 대해 기술하고 있다. """ 영역이 끝나는 부분에서는 한 줄 전체에 """ 문자를 넣고, 바로 위 의 한 줄을 빈 줄로 두어야 한다. 다음 예를 보자::

"""Return a foobang

Optional plotz says to frobnicate the bizbaz first.

"""
한 줄 짜리 docstring의 경우에는 같은 줄에서 """를 닫아도 된다.

버전 관리
소스 파일에 Subversion, CVS, RCS 등이 포함되어 있다면 다음과 같이 버 전을 기록한다::

__version__ = "$Revision$"
# $Source$
위 내용은 모듈의 docstring 바로 다음에 와야 하며, 그 이후에는 프로그 램 코드가 온다. 그리고 이 행의 위와 아래에는 빈 줄 한 개씩이 들어가 야 한다.

명명 규칙
파이썬 라이브러리의 명명 규칙은 난잡하기 때문에, 이 라이브러리에 완 벽하게 일관성을 적용하는 일은 없을 것이다. 어쨌든, 현재 권장되는 명 명 규칙에 대한 표준을 여기 기술한다. 새로운 모듈과 패키지(서드 파티 프레임워크를 포함한)는 이들 표준에 맞추어 작성되어야 하지만, 이미 다 른 스타일로 작성되어 있는 기존 라이브러리는 그 자체의 일관성을 유지 하도록 한다.

표현: 명명 스타일
세상에는 다양한 명명 스타일이 존재한다. 명명 스타일은 이 스타일이 어떤 목적으로 쓰이는지와는 별개로, 어떤 명명 스타일이 쓰이고 있는지 알아보는데 도움이 된다.

다음과 같은 명명 스타일을 흔히 볼 수 있다:

b (한 개의 소문자)
B (한 개의 대문자)
lowercase
lower_case_with_underscores
UPPERCASE
UPPER_CASE_WITH_UNDERSCORES
CapitalizedWords (CapWords 혹은 CamelCase – CamelCase라는 이 름은 글자가 툭 튀어나온 모양 때문에 붙은 이름이다 [3]_). 간혹 StudlyCaps이라고도 불린다.

주의: CapWords에서 약어를 사용할 경우, 약어의 모든 글자를 대문자로 기술한다. 그러므로 HTTPServerError가 HttpServerError를 사용하는 것 보다 낫다.

mixedCase (CapWords와 다른 점은 첫 글자가 소문자라는 것이다)
Capitalized_Words_With_Underscores (이건 별로다!)
서로 연관이 있는 명칭들을 통합하기 위해 짧은 고유 접두사를 사용하는 스타일 또한 존재한다. 파이썬에서는 이러한 스타일이 많이 쓰이지 않고 있으나, 이 문서의 완결성을 위해 언급해둔다. 예를 들어 os.stat() 함수는 전통적으로 st_mode, st_size, st_mtime 등의 이름을 가진 항목이 담긴 튜플을 반환한다. (이들은 POSIX 시스템 호출 구조체 의 필드와 관련이 있음을 강조하기 위해 쓰인 것이다. POSIX에 익숙한 프 로그래머에게는 도움이 된다.)

X11 라이브러리는 public 함수에 모두 X라는 접두사를 사용하고 있다. 파이썬에서 이러한 스타일은 일반적으로 쓸데없는 짓으로 간주된다. 어 트리뷰트와 메서드에 접근할 때에는 객체를 접두사로 사용하며, 함수에 접근할 때에는 모듈 이름을 접두사로 사용하기 때문이다.

또한, 다음과 같이 처음이나 끝에 언더스코어를 사용하는 특별한 형식이 있다 (이들은 보통 대소문자 관련 규칙과 함께 쓰일 수 있다):

_single_leading_underscore: “내부에서 사용한다”는 것을 의미. 예를 들면, from M import *은 언더스코어로 시작하는 객체를 임포 트하지 않는다.

single_trailing_underscore_: 파이썬 키워드와의 충돌을 방지하기 위해 쓰인다. 예::

Tkinter.Toplevel(master, class_='ClassName')
__double_leading_underscore: 클래스 어트리뷰트의 이름을 지으 면 네임 맹글링(name mangling)이 발생한다 (FooBar 클래스 내부에 __boo를 선언하면 이는 _FooBar__boo가 된다; 아래 내용을 참 고하라).

__double_leading_and_trailing_underscore__: 사용자가 관리하는 네임스페이스 내에 있는 “마법(magic)” 객체. __init__, __import__, __file__ 등이 그 예이다. 이러한 이름을 정의해 서 사용하면 안 되며, 이미 문서화되어 있는 항목들만 사용해야 한다.

규정: 명명 규칙
피해야 할 이름

‘l’ (소문자 L), ‘O’ (대문자 O), ‘I’ (대문자 I) 한 글자를 변수 이름으 로 사용하지 않는다.

특정 폰트에서는 이들 글자를 숫자 일, 영과 구별할 수 없다. ‘l’을 굳 이 사용해야 할 경우에는 차라리 ‘L’을 사용하도록 하자.

패키지와 모듈 이름

모듈은 소문자로 이루어진 짧은 이름을 사용해야 한다. 모듈 이름의 가 독성을 높이기 위해 언더스코어를 사용할 수 있다. 파이썬 패키지 또한 소문자로 이루어진 짧은 이름을 사용해야 한다. 하지만 패키지의 경우에 는 언더스코어를 가급적 사용하지 않는다.

위와 같이 하는 이유는 모듈 이름이 파일 이름에 직접적으로 대응되며, 특정한 파일 시스템에서는 대소문자가 무시되고 긴 이름이 잘려나가기 때 문이다. 그러므로 모듈 이름을 가급적 짧게 짓는 것은 중요한 일이다 – 유닉스 시스템에서는 별 문제가 없겠지만 구형 맥, 윈도우, 도스 등으로 이 코드를 옮겼을 때 문제가 될 수 있다.

C/C++로 작성된 확장 모듈이 고수준 인터페이스(예를 들어 좀 더 객체 지 향적인)를 제공하는 파이썬 모듈을 갖추고 있을 경우, 해당 C/C++ 모듈은 앞에 언더스코어를 사용한다 (예를 들면 _socket).

클래스 이름

클래스 이름은 예외 없이 CapWords 규칙을 따른다. 또한 내부에서 사용 하는 클래스는 앞에 언더스코어 문자 하나를 붙인다.

예외 이름

예외는 클래스이므로 클래스 명명 규칙을 그대로 사용한다. 하지만, 예 외 이름의 끝에 “Error”라는 접미사를 사용해야 한다 (예외가 실제로 에 러일 경우에).

전역 변수 이름

(해당 변수는 하나의 모듈 내부에서만 쓰인다고 생각하도록 하자) 이 규 칙은 함수에도 동일하게 적용된다.

from M import * 구문을 통해 사용할 수 있도록 설계된 모듈들은 __all__ 메커니즘을 사용하여 전역적으로 노출되는 것을 방지하거나, 전역 객체에 언더스코어를 붙이는 예전의 규칙을 사용해야 한다 (이들 전 역 객체를 “비 public 모듈”로 표시하고 싶을 경우 사용한다).

함수 이름

함수 이름은 소문자를 사용하며, 가독성을 높이기 위해 각 단어는 언더스 코어로 구분하도록 한다.

mixedCase 스타일의 경우, 이미 mixedCase 스타일을 따르고 있는 모듈 (예: threading.py)에서만 하위 호환성 유지를 위해 허용된다.

함수/메서드 인수

인스턴스 메서드의 첫 번째 인수에는 self를 사용해야 한다.

클래스 메서드의 첫 번째 인수에는 cls를 사용해야 한다.

함수 인수의 이름이 예약어와 충돌할 경우, 일반적으로 인수 이름의 끝에 언더스코어 문자 하나를 추가해 주는 것이 약어를 사용하거나 맞춤법을 어기는 이름을 사용하는 것 보다 낫다. 그러므로 class_를 사용하는 것이 clss를 사용하는 것보다 낫다. (아마 더 나은 방법은 동의어를 사용해서 이름 충돌을 피하는 방법일 것이다.)

메서드 이름과 인스턴스 변수

다음 함수 명명 규칙을 사용한다: 소문자로 된 단어를 사용하며, 각 단어 는 필요에 따라 가독성을 높이기 위해 언더스코어로 구분한다.

비 public 메서드/인스턴스 변수의 경우 앞에 언더스코어 문자를 하나 붙 인다.

서브클래스와의 이름 충돌을 피하기 위해, 앞에 두 개의 언더스코어를 붙 여 파이썬의 네임 맹글링 규칙을 따른다.

파이썬은 이러한 이름에 클래스 이름을 결합하여 네임 맹글링을 수행한다
만약 Foo 클래스가 __a라는 이름의 어트리뷰트를 갖고 있다면, 이 어트리뷰트는 Foo.__a같은 코드로 접근할 수 없다. (집요한 사용자 라면 Foo._Foo__a를 호출하여 이 어트리뷰트에 접근할 수 있다.) 일반적으로 두 개의 언더스코어 문자를 앞에 붙이는 방법은 상속이 일어 나도록 설계된 클래스 내의 어트리뷰트에서 일어나는 이름 충돌을 피할 때에만 사용해야 한다.
주의: __names의 사용에 대해서는 다소 논란이 있다 (아래 내용을 참조).

상수

상수는 보통 모듈 수준에서 정의되며, 대문자 및 단어를 구분하기 위한 언더스코어 문자로 구성된다. MAX_OVERFLOW와 TOTAL 등의 예를 들 수 있다.

상속을 위한 설계

항상 클래스의 메서드와 인스턴스 변수(둘을 합쳐 “어트리뷰트”)가 public인지 public이 아닌지를 결정해야 한다. 결정이 어려울 경우 비 public으로 정의한다. 비 public 어트리뷰트를 public으로 변경하는 것 이 그 반대의 경우보다 쉽다.

public 어트리뷰트는 당신이 만든 클래스를 사용하는 클라이언트들이 실 제로 접근하게 될 어트리뷰트로, 하위 호환성 유지를 고려해야 한다. 비 public 어트리뷰트는 서드 파티가 사용할 수 없도록 설계된 어트리뷰 트이다. 비 public 어트리뷰트는 이후에 변경되거나 제거할 수 있다.

여기서는 “private”이란 단어를 사용하지 않는다. 사실 파이썬에는 private 어트리뷰트가 존재하지 않기 때문이다 (필요 이상의 작업을 동원 하지 않을 경우에 그렇다는 이야기이다).

어트리뷰트의 또 다른 한 가지 유형으로 “서브클래스 API”의 일부를 들 수 있다 (다른 언어에서는 “protected”라고 표현하곤 한다). 클래스들 중 일부는 상속을 위한 부모 클래스로서 설계되며, 클래스의 행동을 구성 하는 요소들을 확장하거나 변경할 수 있다. 이러한 클래스를 설계할 때 어떤 어트리뷰트가 public이 될지, 서브클래스 API의 일부가 될지, 기본 클래스에서만 쓰일지에 대해 명확한 결정을 내리는데 신경써야 한다.

파이썬의 다음과 같은 가이드라인을 마음에 새기도록 하자:

public 어트리뷰트는 언더스코어 문자로 시작해선 안 된다.

public 어트리뷰트의 이름이 예약어와 충돌할 경우, 어트리뷰트 이름의 끝에 언더스코어 문자 하나를 추가한다. 약어나 틀린 맞춤법을 사용하 는 대신 이 방식을 권장한다. (이 규칙과는 상관 없이, ‘cls’는 변수 혹은 인수에서 클래스를 나타내는데 적절한 단어이며, 특히 클래스 메 서드의 첫 번째 인수로 쓰인다.)

주의 1: 위에서 설명한 클래스 메서드의 인수 이름에 대한 권장사항을 살펴보자.

간단한 public 데이터 어트리뷰트의 경우, 복잡한 접근자/변경자 메서 드 대신 어트리뷰트 이름을 직접 노출하는 방법이 가장 좋다. 파이썬 은 향후 개선에 대하여 가장 쉬운 방법을 제공하며, 기능이 늘어날 수 록 간단한 데이터 어트리뷰트가 필요하다는 것을 알아야 한다. 이런 경우, 프로퍼티를 사용하여 실제 기능 구현을 숨기고 사용자는 간단한 데이터 어트리뷰트에 접근하는 문법을 사용할 수 있도록 한다.

주의 1: 프로퍼티는 새로운 스타일의 클래스에서만 동작한다.

주의 2: 기능적 동작에 있어 부작용이 생기지 않도록 주의한다. 하지만 캐시처럼 부작용이 주 기능인 경우는 일반적으로 괜찮다.

주의 3: 큰 계산량을 요구하는 프로퍼티의 사용은 피한다; 호출자는 어 트리뷰트에 대한 접근 비용이 (비교적) 적다고 기대하게 된다.

부모 클래스를 디자인하면서 특정 어트리뷰트가 상속되지 않도록 하고 싶다면, 이들 어트리뷰트의 이름 앞에 두 개의 언더스코어를 붙이는 것 을 고려해보자. 이렇게 하면 파이썬의 네임 맹글링 기능을 사용하게 되며, 어트리뷰트 이름 앞에 클래스의 이름이 맹글링된다. 이 기능은 자식 클래스가 우연히 동일한 어트리뷰트 이름을 사용했을 때 어트리뷰 트 이름 충돌이 일어나는 것을 막아준다.

주의 1: 맹글링에는 단순히 클래스 이름만이 쓰인다는 것에 주의하자. 그러므로 만약 자식 클래스가 동일한 클래스 이름과 어트리뷰트 이름을 사용한다면, 여전히 이름 충돌이 일어날 가능성이 있다.

주의 2: 이름 맹글링을 사용하면 디버깅과 __getattr__() 등의 작 업이 조금 불편해진다. 하지만, 네임 맹글링 알고리즘에 대해서는 이미 정리가 잘 되어 있으므로 직접 필요한 작업을 처리하는 것도 어렵지 않 다.

주의 3: 모든 이가 네임 맹글링을 좋아하는 것은 아니다. 우발적인 이 름 충돌의 방지와, 해당 클래스를 사용하게 될 사람의 편의성 사이에서 형평성을 찾도록 하자.

프로그래밍 권장사항
파이썬 코드는 다른 파이썬 구현 (PyPy, Jython, IronPython, Cython, Psyco 등)에서

예를 들면, CPython의 간단한 문자열 결합(a += b, a = a + b 같은 형태의)은 효율적으로 구현되어 있다. 하지만 Jython에서는 위 구 문이 더 느리게 실행될 수 있으므로, CPython의 구현에 지나치게 의존 해선 안 된다. 성능에 민감한 부분에서 문자열 결합 기능을 사용해야 할 경우 ''.join() 메서드를 대신 사용해야 한다. 이 메서드는 다 양한 파이썬 구현체에서 문자열 결합이 선형적 실행 시간을 갖도록 보 장해 준다.

None과 같은 싱글턴 객체에 대해 비교 연산을 할 때에는 항상 is 혹은 is not 키워드를 사용하며, 동등 비교 연산자를 사용해선 안 된다.

또한, if x is not None의 의미로 if x를 사용하는데 주의해야 한다 – 기본값이 None인 변수나 인수에 무언가 값이 지정되었는지를 검사할 경우를 예로 들 수 있다. 이 값이 다른 객체를 포함하는 타입 (컨테이너의 경우)일 수도 있으며, 그럴 경우 boolean으로 해석되어 false를 반환할 수 있다.

향상된 비교 연산자를 사용해 정렬 기능을 구현할 경우, 특정한 비 교 연산만 구현하는 것 보다는 여섯 개의 연산(__eq__, __ne__, __lt__, __le__, __gt__, __ge__)을 모두 구현하는 것이 최선의 방법이다.

functools.total_ordering() 데코레이터는 구현되지 않은 비교 연 산 메서드를 자동으로 생성해주는 도구를 제공하므로, 이러한 구현에 들어가는 노력을 최소화할 수 있다.

PEP 207은 파이썬이 가정하고 있는 재귀적 규칙에 대해 설명하고 있다. 이에 따르면, 인터프리터는 y > x를 x < y으로, y >= x를 x <= y라는 코드로 변형해서 사용할 수 있으며, x == y와 x != y의 경우 두 인수의 위치를 서로 바꿀 수도 있다. sort()와 min() 명령은 < 연산자를 사용하며, max() 함수는 > 연산자를 사용한다. 어쨌든, 여섯 개의 연산을 모두 구 현하여 다른 맥락에서 혼동이 일어나지 않도록 하는 것이 가장 좋다.

클래스 기반의 예외를 사용한다.

앞으로 작성되는 코드에서는 문자열 예외가 금지되며, 문자열 예외 기 능은 파이썬 2.6에서 제거되었다.

모듈, 패키지는 자신만의 특정 도메인을 기반으로 하는 예외 클래스를 정의해야 한다. 이들 예외 클래스는 파이썬에 내장된 Exception 클래스 를 상속한다. 클래스 docstring을 항상 포함시켜야 한다. 예::

class MessageError(Exception):
    """Base class for errors in the email package."""
예외에는 클래스 이름 규칙이 적용되지만, 이 예외 클래스가 에러를 나 타낼 경우에는 “Error”를 접미사로 붙여야 한다. 에러가 아닌 예외의 경우에는 특별한 접미사를 붙일 필요가 없다.

예외를 발생시키려면 raise ValueError('message') 구문을 사용한다 . 예전의 raise ValueError, 'message'` 구문은 더 이상 사용하지 않는다.

괄호를 사용하는 구문을 권장하는 이유는, 예외의 인수가 긴 문자열이 거나 문자열 형식화를 사용할 경우에 여러 줄을 이어서 작성하기가 좀 더 편리하기 때문이다. 파이썬 3에서 괄호를 사용하지 않은 예외 발 생 구문은 틀린 문법이다.

예외를 처리할(catch) 때에는 범용적인 except: 절 대신, 처리할 특정한 예외를 명시하도록 한다.

예를 들면, 다음과 같은 구문을 사용한다:

try:
    import platform_specific_module
except ImportError:
    platform_specific_module = None
처리할 예외가 지정되지 않은 except: 절은 SystemExit, KeyboardInterrupt 예외를 처리하기 때문에 컨트롤-C를 눌러 프로그램 을 인터럽트할 수 없게 만드는 등 여러 문제를 일으킬 소지가 있다. 만 약 프로그램 에러의 징후를 알리는 모든 예외를 처리하고 싶다면 except Exception:를 사용하도록 하자(처리할 예외가 지정되지 않 은 except 절은 except BaseException: 절과 동일하다).

예외가 지정되지 않은 ‘except’ 절을 사용해야 하는 적절한 경우는 다음 과 같다:

예외 핸들러가 역추적(traceback) 결과를 출력하거나 로그를 남겨야 할 경우; 사용하가 최소한 에러가 일어났음을 인지할 수 있게 된다.

코드에서 일종의 정리 작업을 수행한 다음, raise 명령을 사용 해 해당 예외를 상위 단계로 계속 올려보내려 할 경우. 이 경우에는 try...finally 구문을 사용하는 것이 더 나은 방법일 수 있다.

또한, try/except 절을 사용할 때에는 try 절이 적용되는 코드의 범위를 최소화해야 한다. 이는 버그가 겹치는 일을 막아준다.

좋음::

try:
    value = collection[key]
except KeyError:
    return key_not_found(key)
else:
    return handle_value(value)
나쁨::

try:
    # 코드 적용 범위가 넓다!
    return handle_value(collection[key])
except KeyError:
    # 여기서는 handle_value()이 발생시킨 KeyError 예외를 처리
    return key_not_found(key)
리소스의 점유/해제 외의 작업을 제외하고, 어떤 작업을 할 때든지 별도의 함수/메서드를 통해 문맥(context) 관리자를 호출해야 한다. 예를 들면:
좋음::

         with conn.begin_transaction():
             do_stuff_in_transaction(conn)
나쁨::

         with conn:
             do_stuff_in_transaction(conn)
후자의 예를 살펴보면 enter, exit 메서드가 트랜잭션 이후에 커넥션을 닫는다는 것 외에는 어떤 일을 하는지에 대한 정보를 제공하 지 않는 것을 볼 수 있다. 이러한 경우 명시적인 정보를 제공하는 것 은 중요하다.

string 모듈 대신 문자열 관련 메서드를 사용한다.

문자열 메서드는 항상 좀 더 빠르며 유니코드 문자열과 동일한 API를 공유하고 있다. 파이썬 2.0 이하 버전과의 하위 호환성이 필요한 경우 에는 이 규칙을 무시해도 된다.

문자열의 접두사/접미사를 확인하기 위해 문자열을 직접 자르는 대신, ''.startswith()와 ''.endswith()를 사용한다.

startswith()와 endswith()는 의미가 명확하므로 오류가 발생할 여지가 적다. 예를 들면::

좋음: if foo.startswith('bar'):
나쁨: if foo[:3] == 'bar':
예외적으로, 파이썬 1.5.2에서 동작하는 코드를 작성해야 할 경우는 후 자의 코드를 사용한다(그런 일이 없기를!).

객체 타입 비교 시, 타입을 직접 비교하는 대신 항상 isinstance()를 사용한다. ::

좋음: if isinstance(obj, int):

나쁨: if type(obj) is type(1):
객체가 문자열인지 확인할 경우, 해당 문자열이 유니코드 문자열일 수 도 있다는 점을 명심하자. 파이썬 2.3에서는 str, unicode가 동일한 기본 클래스인 basestring을 상속하므로 다음과 같은 구문을 사용할 수 있다::

if isinstance(obj, basestring):
배열 형태의 타입(문자열, 리스트, 튜플)은 그 내용이 비어있을 때 false를 반환한다는 사실을 활용한다. ::

좋음: if not seq:
      if seq:

나쁨:if len(seq)
     if not len(seq)
공백 문자가 뒤에 따라오는 문자 상수를 사용하지 않는다. 이렇게 뒤 에 붙은 공백 문자는 눈에 잘 띄지 않으며, 일부 편집기(혹은 최신의 reindent.py)에서는 이러한 공백 문자를 제거한다.

==를 사용해서 True/False 값을 부울 값과 비교하지 않는다.

좋음:   if greeting:
나쁨:   if greeting == True:
최악:   if greeting is True:
파이썬 표준 라이브러리는 특정 어노테이션 스타일에 얽매인 함수 어노 테이션을 사용하지 않는다. 그 대신에, 사용자로 하여금 유용한 어노 테이션 스타일을 직접 발견하고 실험해 볼 수 있도록 하고 있다. (역주: 여기에서의 함수 어노테이션은 다른 프로그래밍 언어의 함수 프 로토타입과 비슷하게 함수에 대한 정의를 말한다. PEP 3107을 참고)

함수 어노테이션을 사용하던 초기의 핵심 개발자들은 일관성 없는 임기 응변식 어노테이션 스타일이 존재한다는 것을 발견했다. 예를 들면:

[str]은 이 어노테이션이 문자열 리스트를 나타내는지, str 혹은 None 타입의 값을 나타내는지 모호하다.

open(file:(str,bytes))이란 어노테이션은 str 다음에 bytes 값 을 갖는 2개의 튜플을 나타내는 것이 아니라, bytes 혹은 str울 가질 수 있는 값에 쓰인다.

seek(whence:int)란 어노테이션은 명세가 특정 부분에서는 제약 이 심하고, 혹은 그렇지 않을 때도 있음을 보여준다: int는 지나치게 한정적이며(__index__가 허용하는 모든 것이 허용된다), 동시에 한정적이지 않기도 하다(0, 1, 2 값만 허용된다). 마찬가지로 write(b: bytes)라는 어노테이션 또한 지나치게 한정적이다(버퍼 프로토콜을 허용하는 모든 값을 지원한다).

read1(n: int=None) 어노테이션은 모순적이다. None은 int가 아 니기 때문이다. source_path(self, fullname:str) -> object와 같은 어노테이션은 반환 타입이 무엇인지 혼동할 수 있다.

또한, 어노테이션은 구체화(concrete) 타입과 추상 타입의 사용에 있 어 일관성이 없다: int와 Integral, set/frozenset과 MutableSet/Set 을 들 수 있다.

추상 기본 클래스의 어노테이션 일부는 부정확한 명세를 갖고 있다. 예를 들면, set-to-set 연산은 Set 객체를 필요로 하는데, 이는 Iterable이 아닌 Set 인스턴스를 얻기 위해서이다.

어노테이션은 명세에 포함되어 있으나 테스트가 이루어지지 않았다는 것 또한 문제가 되었다.

대부분의 경우, docstring이 이미 타입 명세에 포함되어 있었으며 함 수 어노테이션보다 더 명확했다. 그 외의 경우, 어노테이션이 삭제 된 후 docstring의 내용이 개선되었다.

이와 같은 함수 어노테이션들은 자동 타입 검사나 인수의 검증을 수 행하는 시스템에서 사용하기에는 너무 주먹구구식이고 일관성이 없었 다. 이러한 어노테이션을 코드에 남겨두면 차후에 자동화된 유틸리티 등을 작성하기 위해 코드를 수정할 때 어려움을 겪을 수 있다.

참조
PEP 7, C 코드 스타일 가이드 (Style Guide for C Code), 반 로섬

배리의 GNU Mailman 스타일 가이드 (Barry’s GNU Mailman style guide) http://barry.warsaw.us/software/STYLEGUIDE.txt

http://www.wikipedia.com/wiki/CamelCase

저작권
이 문서는 퍼플릭 도메인에 속한다.
