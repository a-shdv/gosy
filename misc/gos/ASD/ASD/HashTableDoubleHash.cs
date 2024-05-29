using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ASD;
public class HashTableDoubleHash
{
    private readonly int _size;
    private readonly PersonModel?[] _data;

    public HashTableDoubleHash(int size)
    {
        _size = size;
        _data = new PersonModel?[_size];
    }

    public void Print()
    {
        int i = 1;
        foreach (var element in _data)
        {
            if (element == null || element.IsDeleted)
            {
                Console.WriteLine($"Элемент {i} - null");
            }
            else
            {
                Console.WriteLine($"Элемент {i} - {element.Name} {element.Age}");
            }
            i++;
        }
    }

    public void Insert(PersonModel personModel)
    {
        int index = GetIndex(personModel);

        if (_data[index] == null || _data[index].IsDeleted)
        {
            _data[index] = personModel;
            return;
        }

        for (int i = 0; i < _size; i++)
        {
            index = HelperHash(personModel, i);
            if (_data[index] == null || _data[index].IsDeleted)
            {
                _data[index] = personModel;
                return;
            }
        }


        Console.WriteLine("Таблица переполнена");
    }

    public void Delete(PersonModel personModel)
    {
        for (int i = 0; i < _size; i++)
        {
            int index = HelperHash(personModel, i);
            if (_data[index].Age == personModel.Age && _data[index].Name == personModel.Name && _data[index].IsDeleted == personModel.IsDeleted)
            {
                _data[index].IsDeleted = true;
            }
        }
    }

    public bool Find(PersonModel personModel)
    {


        for (int i = 0; i < _size; i++)
        {
            int index = HelperHash(personModel, i);
            if (_data[index] == null)
            {
                return false;
            }
            if (_data[index].Age == personModel.Age && _data[index].Name == personModel.Name && _data[index].IsDeleted == personModel.IsDeleted)
            {
                return true;
            }
        }

        return false;
    }

    private int GetIndex(PersonModel personModel)
        => (personModel.Age * personModel.Age + personModel.Name.Length * personModel.Name.Length + personModel.Age * personModel.Name.Length) % _size;

    private int HelperHash(PersonModel personModel, int step)
        => (GetIndex(personModel) + step * personModel.Age * personModel.Name.Length) % _size;
}
