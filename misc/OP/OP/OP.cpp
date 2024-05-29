#include <iostream>
#include <list>
#include <vector>

// Структура "plane", представляющая самолёт
struct plane {
	int number; // Номер самолёта
	const char* type; // Тип самолёта
	const char* model; // Модель самолёта
	int gruz; // Грузоподъёмность
	int dalnost; // Дальность полёта

	// Конструктор структуры "plane"
	plane(int _number, const char* _type, const char* _model, int _gruz, int _dalnost) {
		number = _number;
		type = _type;
		model = _model;
		gruz = _gruz;
		dalnost = _dalnost;
	}
};

// Реализация для оценки "3" (массив и std::list)
void array_and_list_example() {
	// Инициализация вектора для хранения самолётов
	std::vector<plane> planeArray;
	// Добавление элементов в вектор
	planeArray.push_back(plane(5, "pass", "11", 10, 100));
	planeArray.push_back(plane(4, "gruz", "22", 10, 200));
	planeArray.push_back(plane(23, "test", "33", 10, 300));
	planeArray.push_back(plane(71, "test", "44", 10, 400));

	// Вывод массива самолётов на экран
	std::cout << "Array of planes:" << std::endl;
	for (size_t i = 0; i < planeArray.size(); i++) {
		std::cout << "Element " << i + 1 << " - number: " << planeArray[i].number
			<< ", type: " << planeArray[i].type
			<< ", model: " << planeArray[i].model
			<< ", gruz: " << planeArray[i].gruz
			<< ", dalnost: " << planeArray[i].dalnost << std::endl;
	}

	// Инициализация списка для хранения самолётов из массива
	std::list<plane> planeList(planeArray.begin(), planeArray.end());
	// Вывод списка самолётов на экран
	std::cout << "\nList of planes:" << std::endl;
	int i = 1;
	for (auto& p : planeList) {
		std::cout << "Element " << i++ << " - number: " << p.number
			<< ", type: " << p.type
			<< ", model: " << p.model
			<< ", gruz: " << p.gruz
			<< ", dalnost: " << p.dalnost << std::endl;
	}
}

// Реализация для оценки "4" (односвязный список)
struct NodeSingly {
	plane* object; // Указатель на объект самолёта
	NodeSingly* next; // Указатель на следующий элемент списка

	// Конструктор узла односвязного списка
	NodeSingly(plane* value) {
		object = value;
		next = nullptr;
	}
};

// Структура "SinglyLinkedList", представляющая односвязный список
struct SinglyLinkedList {
	NodeSingly* head; // Указатель на начало списка

	// Конструктор односвязного списка
	SinglyLinkedList() {
		head = nullptr;
	}

	// Метод добавления элемента в односвязный список
	void add(plane* value) {
		NodeSingly* new_node = new NodeSingly(value);
		if (!head || head->object->number > value->number) {
			new_node->next = head;
			head = new_node;
			return;
		}
		NodeSingly* current = head;
		while (current->next && current->next->object->number <= value->number) {
			current = current->next;
		}
		new_node->next = current->next;
		current->next = new_node;
	}

	// Метод вывода односвязного списка на экран
	void print_list() {
		NodeSingly* current = head;
		int i = 1;
		std::cout << "\nSingly Linked List:" << std::endl;
		while (current) {
			std::cout << "Element " << i++ << " - number: " << current->object->number
				<< ", type: " << current->object->type
				<< ", model: " << current->object->model
				<< ", gruz: " << current->object->gruz
				<< ", dalnost: " << current->object->dalnost << std::endl;
			current = current->next;
		}
	}
};

// Реализация для оценки "5" (двусвязный список)
struct Node {
	plane* object; // Указатель на объект самолёта
	Node* next; // Указатель на следующий элемент списка
	Node* prev; // Указатель на предыдущий элемент списка

	// Конструктор узла двусвязного списка
	Node(plane* value) {
		object = value;
		next = nullptr;
		prev = nullptr;
	}
};

// Структура "DoublyLinkedList", представляющая двусвязный список
struct DoublyLinkedList {
	Node* head; // Указатель на начало списка
	Node* tail; // Указатель на конец списка

	// Конструктор двусвязного списка
	DoublyLinkedList() {
		head = nullptr;
		tail = nullptr;
	}

	// Метод добавления элемента в двусвязный список
	void add(plane* value) {
		Node* new_node = new Node(value);
		if (is_empty()) {
			head = new_node;
			tail = new_node;
		}
		else {
			Node* prevNode = nullptr;
			Node* currentNode = head;

			if (currentNode->object->number > value->number) {
				new_node->next = currentNode;
				new_node->prev = prevNode;
				head = new_node;
				currentNode->prev = new_node;
				return;
			}

			prevNode = currentNode;
			currentNode = currentNode->next;

			while (currentNode != nullptr) {
				if (currentNode->object->number > value->number) {
					new_node->next = currentNode;
					new_node->prev = prevNode;
					currentNode->prev = new_node;
					prevNode->next = new_node;
					return;
				}
				else {
					prevNode = currentNode;
					currentNode = currentNode->next;
				}
			}

			new_node->next = currentNode;

			new_node->prev = prevNode;
			tail = new_node;
			prevNode->next = new_node;
		}
	}

	// Метод вывода двусвязного списка на экран
	void print_list() {
		if (is_empty()) {
			std::cout << "List is empty" << std::endl;
			return;
		}

		Node* currentNode = head;
		int i = 1;
		std::cout << "\nDoubly Linked List:" << std::endl;
		while (currentNode != nullptr) {
			std::cout << "Element " << i++ << " - number: " << currentNode->object->number
				<< ", type: " << currentNode->object->type
				<< ", model: " << currentNode->object->model
				<< ", gruz: " << currentNode->object->gruz
				<< ", dalnost: " << currentNode->object->dalnost << std::endl;
			currentNode = currentNode->next;
		}
	}

	// Метод проверки списка на пустоту
	bool is_empty() {
		return head == nullptr && tail == nullptr;
	}
};

int main() {
	// Оценка "3": Пример использования массива и списка
	array_and_list_example();

	// Оценка "4": Односвязный список
	SinglyLinkedList singlyList;
	plane test1 = plane(5, "pass", "11", 10, 100);
	plane test2 = plane(4, "gruz", "22", 10, 200);
	plane test3 = plane(23, "test", "33", 10, 300);
	plane test4 = plane(71, "test", "44", 10, 400);
	singlyList.add(&test1);
	singlyList.add(&test2);
	singlyList.add(&test3);
	singlyList.add(&test4);
	singlyList.print_list();

	// Оценка "5": Двусвязный список
	DoublyLinkedList doublyList;
	doublyList.add(&test1);
	doublyList.add(&test2);
	doublyList.add(&test3);
	doublyList.add(&test4);
	doublyList.print_list();

	return 0;
}
