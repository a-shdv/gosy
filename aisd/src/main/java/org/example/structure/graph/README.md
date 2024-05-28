## Граф
Граф - это абстрактная математическая структура, используемая для представления сетей связей между объектами. Он состоит из вершин (или узлов) и рёбер (или дуг), которые соединяют эти вершины. Вершины обычно представляют собой объекты, а рёбра - отношения или связи между этими объектами. Графы широко применяются в различных областях, таких как информатика, теория сетей, лингвистика, биология и другие, для моделирования и анализа различных видов взаимодействий и отношений.

Временная сложность алгоритмов на графах зависит от конкретной задачи, которую требуется решить. Например, поиск в ширину и поиск в глубину имеют временную сложность O(V+E)O(V+E), где VV - количество вершин, а EE - количество рёбер в графе. Алгоритмы, такие как алгоритм Дейкстры для нахождения кратчайших путей или алгоритм Флойда-Уоршелла для нахождения всех кратчайших путей между всеми парами вершин, имеют более высокую временную сложность.

Если граф представлен в виде матрицы смежности, то операции, связанные с поиском и обработкой вершин и рёбер, могут иметь временную сложность O(V2)O(V2) для квадратной матрицы смежности и O(V⋅E)O(V⋅E) для матрицы смежности смешанного типа.

Временная сложность алгоритмов также может зависеть от специфических характеристик графа, таких как его плотность (отношение числа рёбер к числу вершин) или структура.

## Алгоритм обхода в глубину
Алгоритм обхода в глубину (или поиска в глубину) - это метод обхода графа или дерева, при котором исследование начинается с выбранной вершины и продолжается до тех пор, пока не будет исследован каждый узел. Он начинает с выбранной вершины и последовательно исследует каждую смежную вершину, пока не дойдет до конечной, после чего возвращается назад и продолжает исследование других неисследованных смежных вершин.

Процесс может быть рекурсивным или использовать стек для отслеживания вершин, которые еще нужно исследовать. Алгоритм обхода в глубину используется для поиска в глубину деревьев, нахождения связных компонентов в графе, топологической сортировки вершин и других задач.

Этот алгоритм широко применяется в теории графов и анализе данных, а также в алгоритмах для решения задач на графах.

## Временная сложность
Временная сложность алгоритма обхода в глубину зависит от числа вершин и рёбер графа.

В худшем случае, когда каждая вершина и каждое ребро графа должны быть проверены, временная сложность алгоритма обхода в глубину составляет O(V+E)O(V+E), где VV - количество вершин, а EE - количество рёбер в графе.

Это связано с тем, что каждая вершина посещается только один раз, а каждое ребро проверяется только один раз при условии, что граф не имеет циклов. Если же граф содержит циклы, то алгоритм обхода в глубину может работать бесконечно, и временная сложность будет зависеть от специфики графа и задачи.
O(V*E)

## Алгоритм обхода в ширину
Алгоритм обхода в ширину (BFS) - это метод обхода графа или дерева, при котором исследование начинается с выбранной вершины, а затем постепенно распространяется на соседние вершины уровнями, начиная с ближайших к начальной вершине.

Алгоритм BFS исследует все вершины на одном уровне графа, прежде чем переходить к исследованию вершин следующего уровня. Он использует очередь для хранения вершин, которые нужно исследовать, и обеспечивает, чтобы вершины были исследованы в порядке их удаленности от начальной вершины.

Алгоритм BFS часто используется для нахождения кратчайшего пути в невзвешенном графе или в графе с равными весами рёбер, а также для поиска в ширину деревьев и для решения других задач, связанных с анализом графов.

Его временная сложность составляет O(V+E)O(V+E), где VV - количество вершин, а EE - количество рёбер в графе.

## Временная сложность
Временная сложность алгоритма обхода в ширину (BFS) составляет O(V+E)O(V+E), где VV - количество вершин, а EE - количество рёбер в графе.

Это объясняется тем, что каждая вершина и каждое ребро графа проверяются ровно один раз во время выполнения алгоритма. Всего выполнится O(V)O(V) операций для проверки вершин и O(E)O(E) операций для проверки рёбер.

Алгоритм BFS работает по слоям, начиная с исходной вершины, и каждая вершина будет добавлена в очередь ровно один раз. Поскольку каждая вершина и каждое ребро проверяются ровно один раз, общая временная сложность алгоритма составляет O(V+E)O(V+E).
**O(V+E)**

### Матрица смежности

**Матрица смежности** — это квадратная матрица, используемая для представления графа. Элементы матрицы показывают, существуют ли рёбра между парами вершин.

Для неориентированных графов матрица смежности симметрична относительно главной диагонали.

#### Пример
Для графа с вершинами {A, B, C, D}:

```
  A B C D
A 0 1 1 0
B 1 0 0 1
C 1 0 0 1
D 0 1 1 0
```

- Вершина A соединена с вершинами B и C.
- Вершина B соединена с вершинами A и D, и так далее.

### Список смежности

**Список смежности** — это способ представления графа, в котором каждая вершина графа содержит список всех вершин, с которыми она соединена.

#### Пример
Для того же графа с вершинами {A, B, C, D}:

```
A: B -> C
B: A -> D
C: A -> D
D: B -> C
```

- Вершина A соединена с вершинами B и C.
- Вершина B соединена с вершинами A и D, и так далее.

### Матрица инцидентности

**Матрица инцидентности** — это матрица, показывающая отношения между вершинами и рёбрами графа. В матрице инцидентности строки соответствуют вершинам, а столбцы — рёбрам.

Для неориентированных графов каждая строка имеет по два ненулевых элемента, обозначающих концы ребра.

#### Пример
Для графа с вершинами {A, B, C, D} и рёбрами {e1, e2, e3, e4} (где e1 соединяет A и B, e2 соединяет A и C, e3 соединяет B и D, и e4 соединяет C и D):

```
     e1 e2 e3 e4
A:  1  1  0  0
B:  1  0  1  0
C:  0  1  0  1
D:  0  0  1  1
```

- Вершина A инцидентна рёбрам e1 и e2.
- Вершина B инцидентна рёбрам e1 и e3, и так далее.

### Преимущества и недостатки

#### Матрица смежности:
- **Преимущества**: Простота реализации и быстрота проверки существования ребра (O(1)).
- **Недостатки**: Требует много памяти (O(V^2)) для хранения, особенно для разреженных графов.

#### Список смежности:
- **Преимущества**: Экономичное использование памяти (O(V + E)) и удобство для хранения разреженных графов.
- **Недостатки**: Проверка существования ребра может быть медленнее (до O(V)).

#### Матрица инцидентности:
- **Преимущества**: Удобна для представления графов с множественными рёбрами между парами вершин.
- **Недостатки**: Требует много памяти (O(V * E)) и менее интуитивна для чтения и понимания.

Эти структуры данных применяются в зависимости от конкретных задач и свойств графа.